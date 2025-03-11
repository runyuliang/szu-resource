#include <stdio.h>
#include <stdlib.h>

#define DISK_SIZE 104857600  // 100 MB

int main() {
    FILE* fp;
    fp = fopen("virtual_disk.img", "wb");
    if (fp == NULL) {
        perror("Failed to create disk file");
        return 1;
    }
    fseek(fp, DISK_SIZE - 1, SEEK_SET);
    fputc('\0', fp);
    fclose(fp);
    printf("100M virtual disk created successfully.\n");
    return 0;
}
