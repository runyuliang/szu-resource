<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>租赁趋势</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <header>
        <h2>Rental Trends</h2>
    </header>

    <nav>
        <!-- ... 省略导航链接 ... -->
    </nav>

    <section>
        <?php
        // 连接到数据库
        include("conn.php");
        // 调用存储过程 RentalTrends
        $sql = "CALL RentalTrends()";
        $result = $conn->query($sql);

        // 输出查询结果
        if ($result->num_rows > 0) {
            echo '<table>';
            echo '<tr><th>制造商</th><th>车型</th><th>学生</th><th>租赁次数</th></tr>';
            while ($row = $result->fetch_assoc()) {
                echo '<tr>';
                echo '<td>' . $row["Make"] . '</td>';
                echo '<td>' . $row["Model"] . '</td>';
                echo '<td>' . ($row["IsStudent"] ? '☑️' : '✖️') . '</td>';
                echo '<td>' . $row["被租赁的次数"] . '</td>';
                echo '</tr>';
            }
            echo '</table>';
        } else {
            echo '<p class="no-rentals">没有租赁数据.</p>';
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