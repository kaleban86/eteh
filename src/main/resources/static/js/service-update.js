(function () {
    // открывается вкладка "входящие обращения"
    //(чтобы по умолчанию было что-то открыто)
    let incomingCallbutton = document.getElementsByClassName('incomingCall')[0];
    incomingCallbutton.click();
})();

function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

(function () {
    // приписывает версию согласно номеру по списку
    let fields = document.querySelectorAll('#Tokyo summary');
    let i = 0;
    for (let field of fields) {
        field.innerHTML = `<div style="display:inline; color:red">Версия ${++i}</div> ${field.innerHTML}`;
    }
})();

(function () {
    //подсвечивает изменённые поля
    let detList = document.querySelectorAll('details');
    let detArr = [];
    if (detList.length > 1) {

        for (let i = 0; i < detList.length; i++) {
            let fieldsArr = [];
            let fields = detList[i].querySelectorAll('.form-control');
            for (let i = 0; i < fields.length; i++) {
                let field = fields[i];
                let val = fields[i].value;
                if (!val) {
                    val = fields[i].innerHTML;
                }
                fieldsArr.push([val, field]);
            }
            detArr.push(fieldsArr);
        }
    }
    //console.log(detArr);
    for (let i = 1; i < detArr.length; i++) {
        let det = detArr[i];
        for (let j = 0; j < det.length; j++) {
            let val = det[j][0];
            let prevVal = detArr[i - 1][j][0];
            let valField = det[j][1];
            if (val != prevVal) {
                valField.style.border = '2px dashed red';
            }
        }
    }
})();
