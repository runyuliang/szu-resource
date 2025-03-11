#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>

#define MAXLINE 4096
#define MAXNAME 50
#define MAXPIPE 16
#define MAXARG 8

struct {
  char *argv[MAXARG];
  char *in, *out;
} cmd[MAXPIPE + 1];

int parse(char *buf, int cmdnum) {
  int n = 0;
  char *p = buf;
  cmd[cmdnum].in = cmd[cmdnum].out = NULL;

  while (*p != '\0') {
    if (*p == ' ') {
      *p++ = '\0';
      continue;
    }

    if (*p == '<') {
      *p = '\0';
      while (*(++p) == ' ')
        ;
      cmd[cmdnum].in = p;
      if (*p++ == '\0') return -1;
      continue;
    }

    if (*p == '>') {
      *p = '\0';
      while (*(++p) == ' ')
        ;
      cmd[cmdnum].out = p;
      if (*p++ == '\0') return -1;
      continue;
    }

    if (*p != ' ' && ((p == buf) || *(p - 1) == '\0')) {
      if (n < MAXARG - 1) {
        cmd[cmdnum].argv[n++] = p++;
        continue;
      } else {
        return -1;
      }
    }
    p++;
  }

  if (n == 0) {
    return -1;
  }

  cmd[cmdnum].argv[n] = NULL;

  return 0;
}

int execute_internal_command(char *cmd) {
  if (strcmp(cmd, "help") == 0) {
    printf("This is a simple shell program.\n");
    printf("Supported internal commands:\n");
    printf("1. help - Display this help message\n");
    printf("2. exit - Exit the shell\n");
    printf("3. cd - Change current directory\n");
    return 1;
  } else if (strcmp(cmd, "exit") == 0) {
    exit(0);
  } else if (strncmp(cmd, "cd ", 3) == 0) {
    char *dir = cmd + 3;
    if (chdir(dir) != 0) {
      perror("cd");
    }
    return 1;
  }
  return 0;
}

void print_prompt() {
  char hostname[MAXNAME];
  gethostname(hostname, MAXNAME);

  char username[MAXNAME];
  getlogin_r(username, MAXNAME);

  char cwd[MAXNAME];
  getcwd(cwd, sizeof(cwd));

  char *home = getenv("HOME");
  if (home != NULL && strstr(cwd, home) == cwd) {
    sprintf(cwd, "~%s", cwd + strlen(home));
  }

  printf("\033[1;34m%s\033[0m@\033[1;32m%s\033[0m:\033[1;36m%s\033[0m$ ",
         username, hostname, cwd);
  fflush(stdout);
}

int main(void) {
  char buf[MAXLINE];
  pid_t pid;
  int fd, i, j, pfd[MAXPIPE][2], pipe_num, cmd_num;
  char *curcmd, *nextcmd;

  while (1) {
    print_prompt();

    if (!fgets(buf, MAXLINE, stdin)) exit(0);
    if (buf[strlen(buf) - 1] == '\n') buf[strlen(buf) - 1] = '\0';
    cmd_num = 0;
    nextcmd = buf;

    while ((curcmd = strsep(&nextcmd, "|"))) {
      if (execute_internal_command(curcmd)) {
        break;
      }

      if (parse(curcmd, cmd_num++) < 0) {
        cmd_num--;
        break;
      }

      if (cmd_num == MAXPIPE + 1) break;
    }

    if (!cmd_num) continue;

    pipe_num = cmd_num - 1;

    for (i = 0; i < pipe_num; i++) {
      if (pipe(pfd[i])) {
        perror("pipe");
        exit(1);
      }
    }

    for (i = 0; i < cmd_num; i++) {
      if ((pid = fork()) == 0) break;
    }

    if (pid == 0) {
      if (pipe_num) {
        if (i == 0) {
          dup2(pfd[0][1], STDOUT_FILENO);
          close(pfd[0][0]);

          for (j = 1; j < pipe_num; j++) {
            close(pfd[j][0]);
            close(pfd[j][1]);
          }

        } else if (i == pipe_num) {
          dup2(pfd[i - 1][0], STDIN_FILENO);
          close(pfd[i - 1][1]);

          for (j = 0; j < pipe_num - 1; j++) {
            close(pfd[j][0]);
            close(pfd[j][1]);
          }

        } else {
          dup2(pfd[i - 1][0], STDIN_FILENO);
          close(pfd[i - 1][1]);

          dup2(pfd[i][1], STDOUT_FILENO);
          close(pfd[i][0]);

          for (j = 0; j < pipe_num; j++)
            if (j != i || j != i - 1) {
              close(pfd[j][0]);
              close(pfd[j][1]);
            }
        }
      }
      if (cmd[i].in) {
        fd = open(cmd[i].in, O_RDONLY);
        if (fd != -1) dup2(fd, STDIN_FILENO);
      }
      if (cmd[i].out) {
        fd = open(cmd[i].out, O_WRONLY | O_CREAT | O_TRUNC, 0644);
        if (fd != -1) dup2(fd, STDOUT_FILENO);
      }

      execvp(cmd[i].argv[0], cmd[i].argv);
      fprintf(stderr, "%s命令无效\n", cmd[i].argv[0]);
      exit(127);
    }

    for (i = 0; i < pipe_num; i++) {
      close(pfd[i][0]);
      close(pfd[i][1]);
    }

    for (i = 0; i < cmd_num; i++) {
      wait(NULL);
    }
  }
}
