google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(drawGenre);
google.charts.setOnLoadCallback(drawCategory);

function drawGenre() {
    let res = [['Категория', 'Количество']];

    for (let i = 0; i < genresString.length; i++) {
        res.push([genresString[i], genresInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Жанры по количеству',
        pieHole: 0.2,

    };

    var chart = new google.visualization.PieChart(document.getElementById('drawGenre'));
    chart.draw(data, options);
}

function drawCategory() {
    let res = [['Жанр', 'Количество']];

    for (let i = 0; i < categoriesString.length; i++) {
        res.push([categoriesString[i], categoriesInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Категории по количеству',
        is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawCategory'));
    chart.draw(data, options);
}