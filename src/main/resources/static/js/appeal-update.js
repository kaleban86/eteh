// (function () {
//     let link = document.querySelector('#download');
//     if ((/\/$/).test(link.href)) link.style.display = 'none';
// })();
// (function () {
//     let link = document.querySelector('#downloadFile');
//     if ((/\/$/).test(link.href)) link.style.display = 'none';
// })();

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
    let date = new Date();
    let year = date.getFullYear();
    let month = ('0' + (date.getMonth() + 1)).slice(-2);
    let day = ('0' + date.getDate()).slice(-2);
    let formedDate = year + '-' + month + '-' + day;

    let picker = document.getElementById('datepicker');
    picker.setAttribute('min', formedDate);

    maxDate = new Date(year + 3, day, month);
    let maxYear = maxDate.getFullYear();
    let maxMonth = ('0' + (maxDate.getMonth() + 1)).slice(-2);
    let maxDay = ('0' + maxDate.getDate()).slice(-2);
    maxFormedDate = maxYear + '-' + maxMonth + '-' + maxDay;

    picker.setAttribute('max', maxFormedDate);
})();

(function () {
    // приписывает версию согласно номеру по списку
    let fields = document.querySelectorAll('#Tokyo summary');
    let i = 0;
    for (let field of fields) {
        field.innerHTML = `<div style="display:inline; color:red">Версия ${++i}</div> ${field.innerHTML}`;
    }
})();

(function () {
    // открывается вкладка "входящие обращения"
    //(чтобы по умолчанию было что-то открыто)
    let incomingCallbutton = document.getElementsByClassName('incomingCall')[0];
    incomingCallbutton.click();
})();
const fileSelect = document.getElementById("fileSelect"),
    fileElem = document.getElementById("fileElem"),
    fileList = document.getElementById("fileList");

fileSelect.addEventListener("click", function (e) {
    if (fileElem) {
        fileElem.click();
    }
    e.preventDefault(); // prevent navigation to "#"
}, false);

fileElem.addEventListener("change", handleFiles, true);

function handleFiles() {
    if (!this.files.length) {
        fileList.innerHTML = "<p>Прикрепить файлы!</p>";
    } else {
        fileList.innerHTML = "";
        const list = document.createElement("ul");
        fileList.appendChild(list);
        for (let i = 0; i < this.files.length; i++) {
            const li = document.createElement("li");
            list.appendChild(li);

            const img = document.createElement("img");
            img.src = URL.createObjectURL(this.files[i]);
            img.height = 60;
            img.onload = function () {
                URL.revokeObjectURL(this.src);
            }
            li.appendChild(img);
            const info = document.createElement("span");
            info.innerHTML = this.files[i].name;
            li.appendChild(info);
        }
    }



}
