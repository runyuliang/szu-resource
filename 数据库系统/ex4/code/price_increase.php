<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>涨价</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <header>
        <h1>涨价</h1>
    </header>

    <nav>
        <!-- ... 省略导航链接 ... -->
    </nav>

    <section>
        <?php
        // 连接到数据库
        include("conn.php");

        // 调用存储过程 IncreasedPrice
        $sql = "CALL IncreasedPrice()";
        $result = $conn->query($sql);
        // 输出结果
        if ($result) {
            echo '<p class="success">涨价成功！</p>';
        } else {
            echo '<p class="error">涨价失败: ' . $conn->error . '</p>';
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