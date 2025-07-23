<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生客户违章情况</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <header>
        <h2>Violations of student customers</h2>
    </header>

    <nav>
        <!-- ... 省略导航链接 ... -->
    </nav>

    <section>
        <?php
        include("conn.php");
        // 调用存储过程 CustomersOnProbation
        $sql = "CALL CustomersOnProbation()";
        $result = $conn->query($sql);

        // 输出查询结果
        if ($result->num_rows > 0) {
            echo '<div class="student-list">';
            while ($row = $result->fetch_assoc()) {
                echo '<div class="student-item">';
                echo '<h3>' . $row["FirstName"] . ' ' . $row["LastName"] . '</h3>';
                echo '<p>Email: ' . $row["Email"] . '</p>';
                echo '</div>';
            }
            echo '</div>';
        } else {
            echo '<p class="no-students">没有学生客户有违章记录.</p>';
        }

        // 关闭数据库连接
        $conn->close();
        ?>
    </section>

    <section class="home-button">
        <div class="home-card">
            <a href="index.php">返回首页</a>
        </div>
    </section>

    <footer>
        <p>&copy; Made by Liang Runyu, 2023.</p>
    </footer>

</body>

</html>