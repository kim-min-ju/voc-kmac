/*
* Gnb
*/
const menuDepth1 = document.querySelectorAll('.menu-depth1>li');
const menuDepth2Box = document.querySelectorAll('.menu-depth2');
const menuDepth2Boxes = [].map.call(menuDepth2Box, function(obj) {
    return obj;
});
let menuDepth2BoxMaxHeight = 0;
//const subMenuBox = document.querySelector('.sub-depth-area');
let boxValues = [];

//메뉴 활성화 시 적용 스타일
function acitveMenu() {
    menuDepth2Box.forEach((box) => {
        boxValues.push(box.offsetHeight);
    });
    menuDepth2BoxMaxHeight = Math.max.apply(null, boxValues);

    menuDepth2Boxes.map((box) => {
        console.log(box);
        box.style.height = menuDepth2BoxMaxHeight+"px";
        //subMenuBox.style.height = menuDepth2BoxMaxHeight+"px";
        box.style.visibility = "visible";
    });
}
//메뉴 비활성화 시 적용 스타일
function inactiveMenu() {
    menuDepth2Boxes.map((box) => {
        box.style.height = 0+"px";
        box.style.visibility = "hidden";
        //subMenuBox.style.height = 0+"px";
    });
}

menuDepth1.forEach((menu) => {
    menu.addEventListener('mouseover', (e) => {
        menu.classList.add("is-active");
        acitveMenu();
    });

    menu.addEventListener('mouseout', (e) => {
        menu.classList.remove("is-active");
        inactiveMenu();
    });
});

/*
* Semantic UI components 함수
*/
function semanticComponents() {
    //체크박스
    $('.ui.checkbox').checkbox();
    //라디오버튼
    $('.selection.dropdown').dropdown();
    //기본 캘린더
    $('.ui.calendar').calendar({
    type: 'date',
    formatter: {
        date: 'YYYY-MM-DD',
        dayHeader: 'YYYY.MMMM',
    },
    startCalendar: $('#rangestart'),
    text: {
        months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        monthsShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        }
    });
    //검색 영역 내 범위 설정 캘린더
    $('#rangestart').calendar({
        type: 'date',
        formatter: {
            dayHeader: 'YYYY.MMMM',
            date: 'YYYY-MM-DD'
        },
        endCalendar: $('#rangeend'),
        text: {
            months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
            monthsShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
          }
      });
      $('#rangeend').calendar({
        type: 'date',
        formatter: {
            date: 'YYYY-MM-DD',
            dayHeader: 'YYYY.MMMM',
        },
        startCalendar: $('#rangestart'),
        text: {
            months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
            monthsShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
          }
      });
      //모달
      $('.ui.modal').modal('show');
}
//공통 컴포넌트 실행
$(document).ready(function() {
    semanticComponents();
});