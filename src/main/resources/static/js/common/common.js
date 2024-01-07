/**
 * 퍼블리셔 작성 소스 수정
 */
//공통 컴포넌트 실행
$(document).ready(function() {
    semanticComponents();
    loginFocusEvnt();

    handler.init();
    handler.removeFile();

    // 엔터클릭 시, 조회버튼 클릭
    $("#searchForm").find("input[type=text]").on('keyup', function(e){
        if(e.key==='Enter'||e.keyCode===13){
            $("#searchForm").find('.btn-search').trigger('click');
        }
    })
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
            dayHeader: 'YYYY.MMMM',
            date: 'YYYY-MM-DD'
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
            dayHeader: 'YYYY.MMMM',
            date: 'YYYY-MM-DD'
        },
        startCalendar: $('#rangestart'),
        text: {
            months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
            monthsShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        }
    });
    //모달
    $('.ui.modal').modal('attach events', '.test.button', 'show');
    //탭
    $('.menu .item').tab();
    //아코디언(타이틀+컨텐츠 접힘펼침에 주로 쓰임)
    $('.ui.accordion').accordion({
        selector: {
            trigger: '.title h3',
        },
        exclusive:false
    });

    //checkbox value 처리 [by kmj]
    $('.ui.checkbox').on('click', function(){
        if($(this).hasClass('checked')) {
            $(this).find('input[type="checkbox"]').val('Y');
        } else {
            $(this).find('input[type="checkbox"]').val('N');
        }
    });
}

/*
* login focus event
*/
function loginFocusEvnt() {
    const loginFields = document.querySelectorAll('.login-area>.field>input');

    loginFields.forEach((menu) => {
        menu.addEventListener('focusin', (e) => {
            menu.parentElement.classList.add("on-focus");
        });
        menu.addEventListener('focusout', (e) => {
            menu.parentElement.classList.remove("on-focus");
        });
    });
}

/*
    * file upload
    */
const dataTranster = new DataTransfer();
const handler = {
    init() {
        const fileInputs = document.querySelectorAll('.file-btn>input');
        const previews = document.querySelectorAll('.file-list');

        fileInputs.forEach((fileInput, index) => {
            fileInput.addEventListener('change', event => {
                const preview = previews[index];
                const files = Array.from(fileInput.files);
                let lastModified;

                files.forEach(file => {
                    lastModified = file.lastModified;
                    //[dev]_a link href에 파일 다운로드 경로 입력이 필요합니다.
                    preview.innerHTML += `
                    <li id="${file.lastModified}">
                        <a href='${file.name}' title='${file.name}' class='file-name' download>${file.name}</a>
                        <button data-index='${file.lastModified}' class='btn-file-remove'></button>
                    </li>`;
                });

                Array.from(files)
                    .forEach(file => {
                        dataTranster.items.add(file);
                    });
            });
        });

        $('.file-list').on('click', '.btn-file-remove', function(e){
            e.preventDefault();
        });
    },

    removeFile: () => {
        document.addEventListener('click', (e) => {
            if(e.target.className !== 'btn-file-remove') return;
            const removeTargetId = e.target.dataset.index;
            const removeTarget = document.getElementById(removeTargetId);

            for(let i=0; i<dataTranster.files.length; i++ ) {
                if(dataTranster.files[i].lastModified == removeTargetId) dataTranster.items.remove(i);
            }
            removeTarget.remove();
        })
    }
};
