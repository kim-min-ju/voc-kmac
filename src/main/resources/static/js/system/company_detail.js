let $customType = 'VOC_TYPE'; //지정코드 유형
$(function () {
    // 초기 설정 및 수행
    init();
});

/**
 * 초기 설정 및 수행 내용
 */
let init = function(){
    let companySeq = localStorage.getItem("companySeq");

    // event 연결 ----------------
    $('.btn-wrap .btn-go-list').on('click', function(){ goList(); });           //목록
    $('.btn-wrap .btn-save-company').on('click', function(){ saveData(); });    //저장
    $('.btn-wrap .btn-delt-company').on('click', function(){ deleteData(); });  //삭제
    $('.btn-wrap .btn-check-company').on('click', function(){ checkCompanyCode(); }); //중복체크
    $('.btn-wrap .btn-cancel-file').on('click', function(){ cancelFile(); });     //파일취소
    $('.btn-wrap .btn-copy-custom').on('click', function(){ popCustom(); }); //초기유형선택 오픈

    $('.btn-wrap .btn-save-custom').on('click', function(){ popAddCustom(); });              //voc 유형등록창 오픈
    $('.btn-wrap .btn-regi-custom1').on('click', function(){ resetCustomData('1'); });  //지정코드_대 신규등록
    $('.btn-wrap .btn-regi-custom2').on('click', function(){ resetCustomData('2'); });  //지정코드_중 신규등록
    $('.btn-wrap .btn-regi-custom3').on('click', function(){ resetCustomData('3'); });  //지정코드_소 신규등록
    $('.btn-wrap .btn-save-custom1').on('click', function(){ saveCustomData('1'); });   //지정코드_대 저장
    $('.btn-wrap .btn-save-custom2').on('click', function(){ saveCustomData('2'); });   //지정코드_중 저장
    $('.btn-wrap .btn-save-custom3').on('click', function(){ saveCustomData('3'); });   //지정코드_소 저장
    $('.btn-wrap .btn-delt-custom1').on('click', function(){ deleteCustomData('1'); }); //지정코드_대 삭제
    $('.btn-wrap .btn-delt-custom2').on('click', function(){ deleteCustomData('2'); }); //지정코드_중 삭제
    $('.btn-wrap .btn-delt-custom3').on('click', function(){ deleteCustomData('3'); }); //지정코드_소 삭제

    $('#registForm').find('#companyCd').on('change', function(){ $('#registForm').find('#dupChkYn').val(''); }); //회사코드 변경시 중복확인 초기화

    // dropbox data setting ----------------
    DropdownUtil.makeCodeList('COM999','USE_YN', $('#registForm').find('.d-useYn'));
    DropdownUtil.makeCodeList('COM999','INDUSTRY_CD', $('#modalCommonCustommstForm').find('.d-industryCd'));

    // tab click
    $('.ui .item').on('click', function() {
        if($(this).attr('data-tab') == 'first') {
            $customType = 'VOC_TYPE';
            $('#modalCommonCustommstForm').find('#customType').val('VOC_TYPE');
            $('.customMstCd').html('VOC유형그룹코드');
            $('.customMstNm').html('VOC유형그룹명');
            $('.vocTypeDiv').removeClass('blind');
            $('.vocActTypeDiv').addClass('blind');
            loadGridVocType(companySeq);
        } else {
            $customType = 'VOC_ACT_TYPE';
            $('#modalCommonCustommstForm').find('#customType').val('VOC_ACT_TYPE');
            $('.customMstCd').html('처리유형그룹코드');
            $('.customMstNm').html('처리유형그룹명');
            $('.vocTypeDiv').addClass('blind');
            $('.vocActTypeDiv').removeClass('blind');
            loadGridVocActType(companySeq);
        }
    });

    setTimeout(function() {
        if(companySeq > 0) searchData(companySeq);
        localStorage.removeItem('companySeq');

        loadGridVocType(companySeq);
    }, 200);
}

/**
 * VOC유형 그리드 옵션
 */
let GRID_OPTIONS_C_VOCTYPE = {
    columns     : [
        { data: 'customNm1',   className: "text-center"   },
        { data: 'customNm2',   className: "text-center"   },
        { data: 'customNm3',   className: "text-center"   },
        { data: 'modUserNm',   className: "text-center"   },
        { data: 'modDt',   className: "text-center"   },
    ],
    initComplete: function( settings, json ) {
        if(json.data.totalCount > 0) {
            $('.btn-save-custom').removeClass('blind');
        } else {
            $('.btn-save-custom').addClass('blind');    //편집버튼 표시
        }
    },
};

/**
 * VOC처리유형 그리드 옵션
 */
let GRID_OPTIONS_VOCACTTYPE = {
    columns     : [
        { data: 'customNm1',   className: "text-center"   },
        { data: 'customNm2',   className: "text-center"   },
        { data: 'modUserNm',   className: "text-center"   },
        { data: 'modDt',   className: "text-center"   },
    ],
    initComplete: function( settings, json ) {
        if(json.data.totalCount > 0) {
            $('.btn-save-custom').removeClass('blind');
        } else {
            $('.btn-save-custom').addClass('blind');    //편집버튼 표시
        }
    },
};

/**
 * VOC유형 그리드 구성
 */
let loadGridVocType = function(companySeq){
    if(companySeq == 0 || ObjectUtil.isEmpty(companySeq)) companySeq = -1;
    let gridOptions = $.extend(true, {}, GRID_OPTIONS_C_VOCTYPE);
    let url = '/kmacvoc/v1/company/custom/list';
    let param = {'companySeq':companySeq, 'customType':'VOC_TYPE'};
    $Grid = gridUtil.loadGrid("listDataTableVocType", gridOptions, url, param);
};

/**
 * VOC처리유형 그리드 구성
 */
let loadGridVocActType = function(companySeq){
    if(companySeq == 0 || ObjectUtil.isEmpty(companySeq)) companySeq = -1;
    let gridOptions = $.extend(true, {}, GRID_OPTIONS_VOCACTTYPE);
    let url = '/kmacvoc/v1/company/custom/list';
    let param = {'companySeq':companySeq, 'customType':'VOC_ACT_TYPE'};
    $Grid = gridUtil.loadGrid("listDataTableVocActType", gridOptions, url, param);
};

/**
 * 목록화면 이동
 */
let goList = function(){
    goPage('/system/companylist');
}

/**
 * 중복체크
 */
let checkCompanyCode = function(){
    let companyCd = $('#registForm').find('#companyCd').val();
    if(ObjectUtil.isEmpty(companyCd)) {
        alert('회사코드를 입력해 주세요.');
        $('#registForm').find('#companyCd').focus();
        return;
    }

    let param = {'companyCdForCheck': companyCd};
    $('#registForm').find('#dupChkYn').val('N');    //중복체크값 초기화

    AjaxUtil.get(
        '/kmacvoc/v1/company/list',
        param,
        function(result){
            if(result && result.data && result.data.list){
                if(result.data.list.length == 0) {
                    $('#registForm').find('#dupChkYn').val('Y');
                    alert('사용가능한 회사코드입니다.');
                } else {
                    alert('회사코드가 중복됩니다.');
                }
            }
        }
    );
}

/**
 * 데이터 조회
 */
let searchData =  function(key){
    AjaxUtil.get(
        '/kmacvoc/v1/company/'+key,
        {},
        function(result){
            if(result && result.data){
                let d = result.data;
                d.useStartDt = Util.dateFormat(d.useStartDt,'-');
                d.useEndDt = Util.dateFormat(d.useEndDt,'-');
                $('#registForm').form('clear');
                $('#registForm').form('set.values', d);
                $('#registForm').find('#companyLogoFile').html(d.companyLogoFileNm);
                $('#registForm').find('#companyLogoFile').attr('href', '/kmacvoc/v1/company/download?companyLogoFileNm='+d.companyLogoFileNm+'&companyLogoPath='+encodeURI( d.companyLogoPath));
                $('#registForm').find('#dupChkYn').val('Y');
                //$('#registForm').find('#companyLogoPath').removeClass('blind');

                $('.btn-delt-company').removeClass('blind');
            }
        }
    );
}

/**
 * 데이터 저장
 */
let saveData = function(){
    let $frm = $('#registForm');
    if($frm.find('#companyCd').val() == '') {
        alert('회사코드는 필수항목입니다.');
        $frm.find('#companyCd').focus();
        return;
    }
    if($frm.find('#dupChkYn').val() != 'Y') {
        alert('회사코드의 중복확인은 필수입니다.'); return;
    }
    if($frm.find('#companyNm').val() == '') {
        alert('회사명(국문)은 필수항목입니다.');
        $frm.find('#companyNm').focus();
        return;
    }
    if($frm.find('#useStartDt').val() > $frm.find('#useEndDt').val()) {
        alert('사용종료일은 사용시작일 이후로 입력해 주십시오.');
        $frm.find('#useEndDt').focus();
        return;
    }

    let form = $("#registForm")[0];
    let formData = new FormData(form);
    let companySeq = $frm.find('#companySeq').val();
    let url = companySeq == '0' ? '/kmacvoc/v1/company/add' : '/kmacvoc/v1/company/modify';

    $.ajax({
        method: "POST",
        url: url,
        data: formData,
        contentType: false,
        processData: false
    })
    .done(function(result) {
        if(result && result.messageCode == '0000'){
            alert(result.data.rtnMessage);
            goList();
        }
    });
}

/**
 * 데이터 삭제
 */
let deleteData = function(){

    if(!confirm('회사정보를 삭제하시겠습니까?')) return;

    let companySeq = $('#registForm').find('#companySeq').val();
    let url = '/kmacvoc/v1/company/remove/'+companySeq;

    AjaxUtil.post(
        url,
        {},
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                goList();
            }
        }
    );
}

/**
 * 파일 선택 취소
 */
let cancelFile = function(){
    $('#registForm').find('#logoFile').val('');
}

/**
 * 초기유형선택 : 회사지정 마스터 모달팝업 오픈
 */
let popCustom = function(){
    LoadGrid.modalCommonCustommst();
    $('#modalCommonCustommstForm').form('clear');
    $('#modalCommonCustommst').modal('show');
}

/**
 * 회사지정 마스터 복사
 */
let copyCustom = function() {
    let customType = $customType;
    let msg = customType == 'VOC_TYPE' ? 'VOC유형그룹' : '처리유형그룹';
    if(!confirm("선택하신 " + msg + " 데이터가 초기유형값으로 저장됩니다. 진행하시겠습니까?")) return;

    let param = {};
    let url = '/kmacvoc/v1/company/custom/copy';
    let companySeq = $('#registForm').find('#companySeq').val();
    let row = $.map($('#listDataTableModalCommonCustom').DataTable().row('.selected').data(), function(item){
        return item;
    });

    param['customMstSeq'] = row[7];
    param['companySeq'] = companySeq;
    param['customType'] = customType;

    AjaxUtil.post(
        url,
        JSON.stringify(param),
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                $('#modalCommonCustommst').modal('hide');

                // grid 조회
                if(customType == 'VOC_TYPE') loadGridVocType(companySeq);
                else loadGridVocActType(companySeq);
            }
        }
    );
}


//VOC유형 처리------------------------------------------------------------------------------------------
/**
 * Edit : 등록/수정을 위한 상세팝업 오픈
 */
let popAddCustom = function(){
    resetCustomData('1');
    searchCustomData('1');

    if($customType == 'VOC_TYPE') $('.ui.modal-voctype').modal('show');
    else $('.ui.modal-vocacttype').modal('show');
}

/**
 * 지정코드 데이터 조회
 */
let searchCustomData =  function(flag){ //modal-voctype
    let $frm = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm'+flag) : $('.modal-vocacttype').find('#customRegistForm'+flag);
    let $frm1 = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm1') : $('.modal-vocacttype').find('#customRegistForm1');
    let $frm2 = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm2') : $('.modal-vocacttype').find('#customRegistForm2');
    let $modal = $customType == 'VOC_TYPE' ? $('.modal-voctype') : $('.modal-vocacttype');
    let param = {};
    param.companySeq = $('#registForm').find('#companySeq').val();
    param.customLevel = flag;
    param.customType = $customType;

    let upperCustomCd2 = $frm1.find('#customCd').val();
    let upperCustomCd3 = $frm2.find('#customCd').val();
    if(flag == '2') param.upperCustomCd = upperCustomCd2;
    if(flag == '3') param.upperCustomCd = upperCustomCd3 == '' ? '-1' : upperCustomCd3;

    AjaxUtil.get(
        '/kmacvoc/v1/company/custom/code/list',
        param,
        function(result){
            if(result && result.data && result.data.list){
                let list = result.data.list;
                let obj = '#selectCustom' + flag;
                $modal.find(obj).html('');
                list.map((d) => {
                    $modal.find(obj).append("<option value='" + d.customCd + "' onclick='javascript:setCustomData("+ flag + "," + JSON.stringify(d) + ")'>" + d.customNm + "</option>");
                });
                $modal.find(obj +" option:eq(0)").prop("selected", true);
                $modal.find(obj +" option:eq(0)").click();

                if(flag == '1') {
                    resetCustomData('2');
                    resetCustomData('3');
                    searchCustomData('2');
                } else if(flag == '2') {
                    resetCustomData('3');
                    searchCustomData('3');
                }
            }
        }
    );
}

/**
 * 신규등록 : 데이터 리셋
 */
let resetCustomData = function(flag) {
    let $frm = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm'+flag) : $('.modal-vocacttype').find('#customRegistForm'+flag);
    $frm.find('#customType').val($customType);
    $frm.find('#customSeq').val('0');
    $frm.find('#upperCustomCd').val('');
    $frm.find('#customCd').val('');
    $frm.find('#customNm').val('');
    $frm.find('#dispOrder').val('');
}

/**
 * 지정코드 데이터 셋팅
 */
let setCustomData = function(flag, d) {
    let $frm = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm'+flag) : $('.modal-vocacttype').find('#customRegistForm'+flag);
    $frm.form('set.values', d);

    if(flag == '1') {
        resetCustomData('2');
        searchCustomData('2');
    } else if(flag == '2') {
        resetCustomData('3');
        searchCustomData('3');
    }
}

/**
 * 지정코드 데이터 저장
 */
let saveCustomData = function(flag){
    let $frm = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm'+flag) : $('.modal-vocacttype').find('#customRegistForm'+flag);
    let $frm1 = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm1') : $('.modal-vocacttype').find('#customRegistForm1');
    let $frm2 = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm2') : $('.modal-vocacttype').find('#customRegistForm2');

    let msg = $customType == 'VOC_TYPE' ? 'VOC유형' : '처리유형';

    if(ObjectUtil.isEmpty($frm.find('#customCd').val())) {
        alert(msg+'코드를 입력해 주세요.');
        $frm.find('#customCd').focus();
        return;
    }
    if(ObjectUtil.isEmpty($frm.find('#customNm').val())) {
        alert(msg+'명을 입력해 주세요.');
        $frm.find('#customNm').focus();
        return;
    }
    if(ObjectUtil.isEmpty($frm.find('#dispOrder').val())) {
        alert('순서를 선택해 주세요.');
        $frm.find('#dispOrder').focus();
        return;
    }

    $frm.find('#companySeq').val($('#registForm').find('#companySeq').val());
    $frm.find('#customLevel').val(flag);
    if(flag == '2') $frm.find('#upperCustomCd').val($frm1.find('#customCd').val());
    if(flag == '3') $frm.find('#upperCustomCd').val($frm2.find('#customCd').val());

    let formData = $frm.serializeObject();
    let url = formData.customSeq == '0' ? '/kmacvoc/v1/company/custom/add' : '/kmacvoc/v1/company/custom/modify';

    AjaxUtil.post(
        url,
        JSON.stringify(formData),
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                searchCustomData(flag);
            }
        }
    );
}

/**
 * 지정코드 데이터 삭제
 */
let deleteCustomData = function(flag){
    let $frm = $customType == 'VOC_TYPE' ? $('.modal-voctype').find('#customRegistForm'+flag) : $('.modal-vocacttype').find('#customRegistForm'+flag);
    let customSeq = $frm.find('#customSeq').val();

    if(ObjectUtil.isEmpty(customSeq)) {
        alert('삭제할 데이터가 존재하지 않습니다.');
        return;
    }

    let msg = $customType == 'VOC_TYPE' ? 'VOC유형코드' : '처리유형코드';
    if(!confirm(msg+'에 연결된 하위코드도 삭제됩니다.\n삭제하시겠습니까?')) return;

    let url = '/kmacvoc/v1/company/custom/remove/'+customSeq;

    AjaxUtil.post(
        url,
        {},
        function(result){
            if(result && result.messageCode == '0000'){
                alert(result.data.rtnMessage);
                searchCustomData(flag);
            }
        }
    );
}

