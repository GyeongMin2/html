require("dotenv").config();
const express = require("express");
const mysql = require("mysql2");
const bodyParser = require("body-parser");
const path = require("path");
const sPort = 80;
const app = express();
const request = require("request");
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname)));

// const db = mysql.createConnection({
//   host: process.env.DB_HOST,
//   user: process.env.DB_USER,
//   password: process.env.DB_PASSWORD,
//   database: process.env.DB_NAME,
//   port: process.env.DB_PORT
// });
const db = mysql.createConnection({
  host: process.env.DB_HOST,
  port: process.env.DB_PORT,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
});

db.connect((err) => {
  if (err) {
    console.log("SQL 연결안됨");
    throw err;
  }
  console.log("SQL 연결됨");
});

// db 검색
app.get("/getWeather", (req, res) => {
  let city = req.query.city;
  let date = req.query.date;

  // SQL 쿼리 작성
  let sql = `
  SELECT tct.x, tct.y
  FROM tbl_concat_test tct
  WHERE MATCH(location2) AGAINST('${city}' IN boolean mode); 
  `;

  // 데이터베이스 쿼리 실행
  db.query(sql, (err, result) => {
    if (err) {
      console.log("쿼리 에러 발생", err);
      res.status(500).json({ error: "Database query error" });
      return;
    }

    console.log("쿼리 결과", result);

    if (result.length > 0) {
      let x = result[0].x;
      let y = result[0].y;

      // 기상청 API 호출
      let url =
        "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
      let queryParams =
        "?" + encodeURIComponent("serviceKey") + "=" + process.env.SERVICE_KEY;
      queryParams +=
        "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1");
      queryParams +=
        "&" + encodeURIComponent("numOfRows") + "=" + encodeURIComponent("1");
      queryParams +=
        "&" + encodeURIComponent("dataType") + "=" + encodeURIComponent("JSON");
      queryParams +=
        "&" + encodeURIComponent("base_date") + "=" + encodeURIComponent(date);
      queryParams +=
        "&" + encodeURIComponent("base_time") + "=" + encodeURIComponent("0600");
      queryParams +=
        "&" + encodeURIComponent("nx") + "=" + encodeURIComponent(x);
      queryParams +=
        "&" + encodeURIComponent("ny") + "=" + encodeURIComponent(y);

      request(
        {
          url: url + queryParams,
          method: "GET",
        },
        function (error, response, body) {
          if (error) {
            res.status(500).json({ error: "API 호출 실패" });
            return;
          }
          console.log("기상청 API 응답:", body);
          res.json(JSON.parse(body));
        }
      );
    } else {
      res.status(404).json({ error: "도시를 찾을 수 없음" });
    }
  });
});

// 서버 실행
app.listen(sPort, () => {
  console.log(`${sPort}에서 실행됨`);
});
