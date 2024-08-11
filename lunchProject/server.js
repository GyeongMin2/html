const express = require("express");
const mysql = require("mysql2");
const bodyParser = require("body-parser");
const path = require("path");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname)));

const db = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "kgm101",
  database: "todaylunch",
  port: 3306,
});

db.connect((err) => {
  if (err) {
    console.log("sql 연결안됨ㅇㅇ");
    throw err;
  }
  console.log("sql 연결됨");
});

// db 검색
app.get("/getLunchMenu", (req, res) => {
  let date = req.query.date;
  let sql = `SELECT
    today_date,
    menu_rice,
    menu_soup,
    menu_main1,
    menu_main2,
    menu_side1,
    menu_side2,
    menu_side3,
    menu_side4,
    type_meal
  FROM bap 
  WHERE today_DATE='${date}';`;

  db.query(sql, [date], (err, result) => {
    if (err) {
      console.log("쿼리에러남", err);
    }
    console.log('쿼리결과',result);
    res.json(result);
  });
});
// 서버 실행
app.listen(3000, () => {
  console.log("3000포트");
});