let $Grid        = null;

let GRID_OPTIONS = {
    columns     : [
        { data: 'authSeq',    className: "select-checkbox",
            'render': function (data, type, full, meta) {
                return '<input type="radio" value="'+data+'" />';
            }
        },
        //{ data: 'authSeq',   className: "text-center"   },
        { data: 'authCd',   className: "text-center"   },
        { data: 'authNm',   className: "text-center"   },
        { data: 'authDesc',   className: "text-center"   },
        { data: 'useYn',   className: "text-center"   },
        { data: 'regUserNm',   className: "text-center" },
        { data: 'regDt',   className: "text-center" },
        { data: 'modUserNm',   className: "text-center" },
        { data: 'modDt',   className: "text-center" },
    ],
    //scrollY             : '300px',
    scrollCollapse      : true,
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
    //$('.modal-auth').modal('hide').modal('hide dimmer');

    // datepicker loading 적용
    // UIUtil.loadDatepicker($("#selectedDt"));

    // event 연결 ----------------
    $('.btn-search').on('click', function(){ ajaxLoadData(); });
    $('.btn-wrap .btn-add').on('click', function(){ popAddAuth(); });
    $('.btn-save').on('click', function(){ saveData(); });
    $('.btn-wrap .btn-dtl').on('click', function(){ popDtlAuth(); });
    $('.btn-delt').on('click', function(){ deleteData(); });

    ajaxLoadData();

}

/**
 * 지정된 날짜의 방문자 통계 정보를 조회
 *  => chart 및 grid를 그린다.
 */
let ajaxLoadData = function(){
    let param = {};
    param.useYn = $('#searchForm').find('#useYn').val();
    param.authCd = $('#searchForm').find('#authCd').val();
    param.authNm = $('#searchForm').find('#authNm').val();
console.log(param);
    AjaxUtil.get(
        '/kmacvoc/v1/auth/list',
        param,
        function(result){
            if(result && result.data && result.data.list){
                // grid를 그린다.
                loadGridOneDay(result.data.list);
            }
        }
    );
}


/**
 * Grid 구성
 *
 * @param loadDataSet grid를 그릴 dataset
 */
let loadGridOneDay = function(loadDataSet){

    let gridOptions = $.extend(true, {}, GRID_OPTIONS);

    // gird 생성시.
    if($Grid == null){
        /*
                if(ObjectUtil.isEmpty(loadDataSet)){
                    gridOptions.ajax = {
                        url   : '/statistics/getStatisticsOneDayDatas',
                        type  : 'get',
                        data  : {
                            // reload시에 변경된 parameter를 적용하기 위해 function(){return...;} 형태로 구성
                            date : function(){ return $("#selectedDt").val();}
                        },
                        dataType: "JSON"
                    }
                }
                else {
                    gridOptions_.data = loadDataSet;
                }
        */

        gridOptions.data = loadDataSet;

        // grid 생성
        $Grid = gridUtil.loadGrid("listDataTable", gridOptions);
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
 * 등록/수정을 위한 권한상세팝업 오픈
 */
let popAddAuth = function(){
    $('#registForm').form('reset');
    $('.ui.modal-auth').modal('show');
}

/**
 * 권한상세 팝업
 */
let popDtlAuth =  function(){
    console.log('$selectedRowData',$selectedRowData);
    $('#registForm').form('reset');
    $('#registForm').form('set.values', {
        authCd: $selectedRowData.authCd,
        authDesc: $selectedRowData.authDesc,
        authNm: $selectedRowData.authNm,
        authSeq: $selectedRowData.authSeq,
        modDt: $selectedRowData.modDt,
        modUserNm: $selectedRowData.modUserNm,
        //modUserNo: $selectedRowData.modUserNo,
        regDt: $selectedRowData.regDt,
        regUserNm: $selectedRowData.regUserNm,
        //regUserNo: $selectedRowData.regUserNo,
        useYn: $selectedRowData.useYn,
    });
    $('.btn-delt').removeClass('blind');
    $('.ui.modal-auth').modal('show');
}

/**
 * 데이터 저장
 */
let saveData = function(){
    let formData = $('#registForm').serializeObject();
    let url = formData.authSeq == '' ? '/kmacvoc/v1/auth/add' : '/kmacvoc/v1/auth/modify';

    AjaxUtil.post(
        url,
        JSON.stringify(formData),
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                $('.ui.modal-auth').modal('hide');
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

    if(!confirm('권한정보를 삭제하시겠습니까?')) return;

    let authSeq = $('#registForm').find('#authSeq').val();
    let url = '/kmacvoc/v1/auth/remove/'+authSeq;

    AjaxUtil.post(
        url,
        {},
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                $('.ui.modal-auth').modal('hide');
                // grid를 그린다.
                ajaxLoadData();
            }
        }
    );
}
