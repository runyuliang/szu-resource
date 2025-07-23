<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available vehicles</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <?php
    include("conn.php");
    // 查询所有可用车辆
    $sql = "SELECT * FROM Car WHERE CarId NOT IN (SELECT CarID FROM Rentals WHERE DropDate IS NULL)";
    $result = $conn->query($sql);
    echo '<h2> Available vehicles </h2>';
    // 输出结果
    if ($result->num_rows > 0) {
        echo '<div class="car-list">';
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
        echo '<p class="no-cars">没有可用车辆.</p>';
    }

    // 关闭数据库连接
    $conn->close();
    ?>
    <section class="home-button">
        <div class="home-card">
            <a href="index.php">返回首页</a>
        </div>
    </section>
</body>

</html>