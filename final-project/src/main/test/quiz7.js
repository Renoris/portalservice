buggame();

function buggame() {
  let $point = document.getElementById('point');
  let $life = document.getElementById('life');
  const $box = document.querySelector('.box');
  const $bug = document.getElementById('bugwrapper');
  const $timer = document.getElementById('timer');
  let gogo;
  let score = 0;
  let life = 10;
  let timer = 10;

  function restartsetting() {
    score = 0;
    life = 10;
    $point.innerHTML = score;
    clearInterval(gogo);
    lifegangsin();
    timergangsin();
  }

  function starttimer() {
    gogo = setInterval(function () {
      if (timer > 0) {
        timer--;
        $timer.innerHTML = timer;
      } else if (timer <= 0) {
        bugmove();
        life--;

        lifegangsin();
        timergangsin();
      }
    }, 150);
  }

  $box.addEventListener('click', (event) => {
    life--;
    lifegangsin();
    bugmove();
    timergangsin();
  });

  function lifegangsin() {
    $life.innerHTML = life;
    if (life == 0) {
      alert('you lose');
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
    if (score == 0) {
      starttimer();
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
    //난수만드는 긁어온 코드 https://webisfree.com/2016-05-30/[자바스크립트]-난수-랜덤-숫자-만들기-예제
    var ranNum = Math.floor(Math.random() * (max - min + 1)) + min;
    return ranNum;
  };
}
