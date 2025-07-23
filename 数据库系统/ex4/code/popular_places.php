<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>热门地点</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <header>
        <h2>Popular Places</h2>
    </header>

    <nav>
        <!-- ... 省略导航链接 ... -->
    </nav>

    <section>
        <?php
        include("conn.php");
        // 调用存储过程 PopularLocations
        $sql = "CALL PopularLocations()";
        $result = $conn->query($sql);

        // 输出查询结果
        if ($result->num_rows > 0) {
            echo '<table>';
            echo '<tr><th>地点ID</th><th>地址</th><th>电话</th><th>租车次数</th></tr>';
            while ($row = $result->fetch_assoc()) {
                echo '<tr>';
                echo '<td>' . $row["Location ID"] . '</td>';
                echo '<td>' . $row["Street Address"] . '</td>';
                echo '<td>' . $row["Telephone"] . '</td>';
                echo '<td>' . $row["Number of Rentals"] . '</td>';
                echo '</tr>';
            }
            echo '</table>';
        } else {
            echo '<p class="no-locations">没有热门地点.</p>';
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