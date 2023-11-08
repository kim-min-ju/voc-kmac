let $Grid        = null;

let GRID_OPTIONS = {
    columns     : [
        { data: 'userSeq',    className: "select-checkbox",
            'render': function (data, type, full, meta) {
                return '<input type="radio" value="'+data+'" />';
            }
        },
        { data: 'companyCd',   className: "text-center"   },
        { data: 'empTypeCd',   className: "text-center"   },
        { data: 'userId',   className: "text-center"   },
        { data: 'empNo',   className: "text-center"   },
        { data: 'userNm',   className: "text-center"   },
        { data: 'userNmEn',   className: "text-center"   },
        { data: 'deptNm',   className: "text-center"   },
        { data: 'dutyNm',   className: "text-center"   },
        { data: 'positionNm',   className: "text-center"   },
        { data: 'titleNm',   className: "text-center"   },
        { data: 'joinDt',   className: "text-center"   },
        { data: 'retireDt',   className: "text-center"   },
        { data: 'emailAddr',   className: "text-center"   },
        { data: 'useYn',   className: "text-center"   },
        { data: 'regUserNm',   className: "text-center" },
        { data: 'regDt',   className: "text-center" },
        { data: 'modUserNm',   className: "text-center" },
        { data: 'modDt',   className: "text-center" },
    ],
    //scrollY             : '300px',
    //scrollCollapse      : true,
    scrollX: '100%',
    scrollXInner: '2300px',
};

$(function () {
    // 초기 설정 및 수행
    init();
});


/**
 * 초기 설정 및 수행 내용
 */
let init = function(){
    // 초기 화면 구성 ----------------
    //$('.modal-user').modal('hide').modal('hide dimmer');

    // datepicker loading 적용
    // UIUtil.loadDatepicker($("#selectedDt"));

    // event 연결 ----------------
    $('.btn-search').on('click', function(){ ajaxLoadData(); });
    $('.btn-wrap .btn-add').on('click', function(){ popAddUser(); });
    $('.btn-save').on('click', function(){ saveData(); });
    $('.btn-wrap .btn-dtl').on('click', function(){ popDtlUser(); });
    $('.btn-delt').on('click', function(){ deleteData(); });

    // dropbox data setting ----------------
    makeDropdownList('COM_999','USE_YN', $('#registForm').find('.d-employmentYn'));
    makeDropdownList('COM_999','USE_YN', $('#registForm').find('.d-useYn'));

    ajaxLoadData();

}

/**
 * 지정된 날짜의 방문자 통계 정보를 조회
 *  => chart 및 grid를 그린다.
 */
let ajaxLoadData = function(){
    let param = $('#searchForm').form('get.values');

    AjaxUtil.get(
        '/kmacvoc/v1/user/list',
        param,
        function(result){
            if(result && result.data && result.data.list){
                // grid를 그린다.
                loadGrid(result.data.list);
            }
        }
    );
}


/**
 * Grid 구성
 *
 * @param loadDataSet grid를 그릴 dataset
 */
let loadGrid = function(loadDataSet){

    let gridOptions = $.extend(true, {}, GRID_OPTIONS);

    // gird 생성시.
    if($Grid == null){
        gridOptions.data = loadDataSet;

        // grid 생성
        $Grid = gridUtil.loadGrid("listDataTableUser", gridOptions);
    }
    // reload 시.
    else {
        // ajax가 정의 되어 있을 때
        if(gridOptions.ajax){
            $Grid.ajax.reload();
        }
        // ajax가 정의되지 않고 dataSet으로 그릴 때
        else {
            $Grid.clear();                  // clear
            $Grid.rows.add(loadDataSet);    // Add new data
            $Grid.columns.adjust().draw();  // Redraw the DataTable
        }
    }
};

/**
 * 등록/수정을 위한 사용자상세팝업 오픈
 */
let popAddUser = function(){
    $('#registForm').form('clear');
    $('.ui.modal-user').modal('show');
}

/**
 * 사용자상세 팝업
 */
let popDtlUser =  function(){
    if($selectedRowData.userSeq == undefined) {
        alert('목록을 선택해 주세요.');
        return;
    }
    AjaxUtil.get(
        '/kmacvoc/v1/user/'+$selectedRowData.userSeq,
        {},
        function(result){
            if(result && result.data){
                $('#registForm').form('clear');
                $('#registForm').form('set.values', result.data);
                $('.btn-delt').removeClass('blind');
                $('.ui.modal-user').modal('show');
            }
        }
    );
}

/**
 * 데이터 저장
 */
let saveData = function(){
    let formData = $('#registForm').serializeObject();
    let url = formData.userSeq == '' ? '/kmacvoc/v1/user/add' : '/kmacvoc/v1/user/modify';

    AjaxUtil.post(
        url,
        JSON.stringify(formData),
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                $('.ui.modal-user').modal('hide');
                // grid를 그린다.
                ajaxLoadData();
            }
        }
    );
}

/**
 * 데이터 삭제
 */
let deleteData = function(){

    if(!confirm('사용자정보를 삭제하시겠습니까?')) return;

    let userSeq = $('#registForm').find('#userSeq').val();
    let url = '/kmacvoc/v1/user/remove/'+userSeq;

    AjaxUtil.post(
        url,
        {},
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                $('.ui.modal-user').modal('hide');
                // grid를 그린다.
                ajaxLoadData();
            }
        }
    );
}
