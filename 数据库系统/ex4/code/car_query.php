<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicle inquiry</title>
    <link rel="stylesheet" href="style.css">

</head>

<body>

    <header>
        <h2>Vehicle inquiry</h2>
    </header>

    <nav>
        <!-- ... 省略导航链接 ... -->
    </nav>

    <section>
        <form action="" method="post">
            <label for="queryType">选择查询类型:</label>
            <select name="queryType" id="queryType">
                <option value="passengerCount">车载人数</option>
                <option value="description">汽车描述</option>
            </select>
            <br>

            <label for="queryValue">输入查询值:</label>
            <input type="text" name="queryValue" id="queryValue" required>
            <br>

            <input type="submit" value="查询">
        </form>

        <?php
        // 检查是否提交了查询表单
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            // 连接到数据库
            include("conn.php");

            // 获取用户选择的查询类型和值
            $queryType = $_POST["queryType"];
            $queryValue = $_POST["queryValue"];

            // 根据查询类型执行相应的 SQL 查询
            if ($queryType == "passengerCount") {
                // 调用存储过程 NumberOfPassengers
                $sql = "CALL NumberOfPassengers(?)";
            } elseif ($queryType == "description") {
                $sql = "SELECT * FROM Car WHERE Description LIKE ?";
                $queryValue = "%" . $queryValue . "%"; // 模糊搜索
            }

            // 使用预处理语句防止 SQL 注入攻击
            $stmt = $conn->prepare($sql);
            $stmt->bind_param("s", $queryValue);
            $stmt->execute();
            $result = $stmt->get_result();

            // 输出查询结果
            if ($result->num_rows > 0) {
                echo '<div class="car-list">';
                if ($queryType == "description") {
                    while ($row = $result->fetch_assoc()) {
                        echo '<div class="car-item">';
                        echo '<h3>' . $row["Make"] . ' ' . $row["Model"] . '</h3>';
                        echo '<p>Car ID: ' . $row["CarId"] . '</p>';
                        echo '<p>Description: ' . $row["Description"] . '</p>';
                        echo '<p>Price Per Hour: $' . $row["PricePerHour"] . '</p>';
                        echo '<p>Price Per Day: $' . $row["PricePerDay"] . '</p>';
                        echo '<p>Number Of Passengers: ' . $row["NumberOfPassengers"] . '</p>';
                        echo '</div>';
                    }
                    echo '</div>';
                } else {
                    while ($row = $result->fetch_assoc()) {
                        echo '<div class="car-item">';
                        echo '<h3>' . $row["Make"] . ' ' . $row["Model"] . '</h3>';
                        echo '<p>Car ID: ' . $row["CarId"] . '</p>';
                        echo '<p>Price Per Hour: $' . $row["PricePerHour"] . '</p>';
                        echo '<p>Number Of Passengers: ' . $row["NumberOfPassengers"] . '</p>';
                        echo '</div>';
                    }
                    echo '</div>';
                }
            } else {
                echo '<p class="no-cars">没有符合条件的车辆.</p>';
            }

            // 关闭数据库连接
            $stmt->close();
            $conn->close();
        }
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