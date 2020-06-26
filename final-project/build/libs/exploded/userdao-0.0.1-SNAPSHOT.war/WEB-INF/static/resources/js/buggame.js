let $point = document.getElementById('point');
let $life = document.getElementById('life');
const $box = document.querySelector('.box');
const $bug = document.getElementById('bug');
const $timer = document.getElementById('timer');
const $finalscore = document.getElementById('final-score');
let gogo;
let score = 0;
let life = 10;
let timer = 10;
let finalscore = 0;


function sendPost(action) {
  var form = document.createElement('form');
  form.setAttribute('method', 'post');
  form.setAttribute('action', action);
  document.charset = "utf-8";
  var hiddenField = document.createElement('input');
  hiddenField.setAttribute('type', 'hidden');
  hiddenField.setAttribute('name', 'score');
  hiddenField.setAttribute('value', finalscore);
  form.appendChild(hiddenField);
  document.body.appendChild(form);
  form.submit();
}


buggame();
function buggame() {
  function restartsetting() {
    if (score > finalscore) {
      finalscore = score;
      $finalscore.innerHTML = finalscore;
    }
    score = 0;
    life = 10;
    $point.innerHTML = score;
    clearInterval(gogo);
    lifegangsin();
    timergangsin();
    sendPost("/buggame");
  }

  function starttimer() {
    gogo = setInterval(function () {
      if (timer > 0) {
        timer--;
        $timer.innerHTML = timer;
      } else if (timer <= 0) {
        life--;
        lifegangsin();
        timergangsin();
      }
    }, 70);
  }

  $box.addEventListener('click', (event) => {
    life--;
    lifegangsin();
    timergangsin();
  });

  function lifegangsin() {
    $life.innerHTML = life;
    if (life == 0) {
      alert('게임 종료');
      restartsetting();
    }
  }

  function timergangsin() {
    timer = 10;
    $timer.innerHTML = timer;
  }

  function scoreup() {
    score++;
    life++;
    $point.innerHTML = score;
    $life.innerHTML = life;
  }

  $bug.addEventListener('click', (event) => {
    console.log("작동하니?")
    if (score == 0) {
      starttimer();
      console.log("작동안하니?")
    }
    bugmove();
    scoreup();
    timergangsin();
  });

  function bugmove() {
    $bug.style.left = `${generateRandom(10, 390)}px`;
    $bug.style.top = `${generateRandom(10, 390)}px`;
  }

  var generateRandom = function (min, max) {
    var ranNum = Math.floor(Math.random() * (max - min + 1)) + min;
    return ranNum;
  };
}