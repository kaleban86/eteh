
function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("myTable");
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
    no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /*Loop through all table rows (except the
        first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
            one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
            based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
            and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /*If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

(function() {
    // окрашивает просроченные обращения в красный
    let day = new Date();
    let nowDate = new Date( day.getFullYear(), day.getMonth(),
        day.getDate() );
    let nowTimeUnix = nowDate.getTime();
    let trArr = document.querySelectorAll('#myTable tr');
    for (let i = 1; i < trArr.length; i++) {
        let tdArr = trArr[i].querySelectorAll('td');
        let answerDate = tdArr[3].innerHTML;
        let status = tdArr[5].innerHTML;
        let answerUnixTime = new Date(
            answerDate.substr(6, 4),
            answerDate.substr(3, 2) - 1,
            answerDate.substr(0, 2)
        ).getTime();
        if ( ( status == 'Новое' || status == 'В работе' )
            && ( nowTimeUnix > answerUnixTime ) ) {
            //просрочено
            tdArr[5].style.backgroundColor = '#fa6565';
        }
    }
})();


function tableSearch() {
    var phrase = document.getElementById('search-text');
    var table = document.getElementById('myTable');
    var regPhrase = new RegExp(phrase.value, 'i');
    var flag = false;
    for (var i = 1; i < table.rows.length; i++) {
        flag = false;
        for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
            flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
            if (flag) break;
        }
        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }

    }
}

(function pickers() {
    //реализация фильтраций
    (function statusPicker() {
        //фильтрация по статусу
        let picker = document.getElementById('statuspicker');
        picker.onclick = function(element) {
            let status = element.target.innerHTML;
            let trArr = document.querySelectorAll('#myTable tr');
            for (let i = 1; i < trArr.length; i++) {
                let tdArr = trArr[i].querySelectorAll('td');
                let tableStatus = tdArr[5].innerHTML;
                if (status == tableStatus) {
                    trArr[i].style.display = '';
                } else {
                    trArr[i].style.display = 'none';
                }
            }
        }
    })();
    (function organizationPicker() {
        //фильтрация по организации
        //происходит по атрибутам value (...<option value="газпром">...)
        let picker = document.getElementById('organizationpicker');
        picker.onchange = function() {
            let organization = picker.value;
            let trArr = document.querySelectorAll('#myTable tr');
            for (let i = 1; i < trArr.length; i++) {
                let tdArr = trArr[i].querySelectorAll('td');
                let tableOrganization = tdArr[1].innerHTML;
                if (organization ==''|| organization == tableOrganization) {
                    trArr[i].style.display = '';
                } else {
                    trArr[i].style.display = 'none';
                }
            }
        }
    })();
    (function datePickers() {
        let pickers = {
            setDefaultDates: function() {
                let pickerFrom = document.getElementById('datepicker');
                let pickerTo = document.getElementById('datepicker1');
                pickerFrom.value = table.getMinDate();
                pickerTo.value = table.getMaxDate();
                //console.log(table.getMinDate(), table.getMaxDate());
            },
            activate: function() {
                let datePickerFrom = document.getElementById("datepicker");
                let datePickerTo = document.getElementById("datepicker1");

                datePickerFrom.onchange = pickers.onchange;
                datePickerTo.onchange = pickers.onchange;
            },
            onchange: function() {
                let datePickerFrom = document.getElementById("datepicker");
                let datePickerTo = document.getElementById("datepicker1");
                let [yearFrom, monthFrom, dayFrom] = datePickerFrom.value.split('-').map(elem => +elem);
                let dateFrom = new Date(yearFrom, monthFrom, dayFrom);
                let [yearTo, monthTo, dayTo] = datePickerTo.value.split('-').map(elem => +elem);
                let dateTo = new Date(yearTo, monthTo, dayTo);

                let trArr = document.querySelectorAll('#myTable tr');
                for (let i = 1; i < trArr.length; i++) {
                    let tdArr = trArr[i].querySelectorAll('td');
                    let [day, month, year] = tdArr[2].innerHTML.split('.');
                    let tableDate = new Date(year, month, day);
                    if (dateFrom.getTime() <= tableDate.getTime() && dateTo.getTime() >= tableDate.getTime()) {
                        trArr[i].style.display = '';
                    } else {
                        trArr[i].style.display = 'none';
                    }
                }
            }
        };
        let table = {
            getMinDate: function() {
                let trArr = document.querySelectorAll('#myTable tr');
                let minDate;
                let tableYear, tableMonth, tableDay;
                for (let i = 1; i < trArr.length; i++) {
                    let tdArr = trArr[i].querySelectorAll('td');
                    let [day, month, year] = tdArr[2].innerHTML.split('.');
                    let tableDate = new Date(year, month, day);
                    if (minDate == undefined) {
                        minDate = tableDate.getTime();
                        [tableDay, tableMonth, tableYear] = [day, month, year];
                    } else if (minDate > tableDate.getTime()) {
                        minDate = tableDate.getTime();
                        [tableDay, tableMonth, tableYear] = [day, month, year];
                    }
                }
                return `${tableYear}-${tableMonth}-${tableDay}`;
            },
            getMaxDate: function() {
                let trArr = document.querySelectorAll('#myTable tr');
                let maxDate;
                let tableYear, tableMonth, tableDay;
                for (let i = 1; i < trArr.length; i++) {
                    let tdArr = trArr[i].querySelectorAll('td');
                    let [day, month, year] = tdArr[2].innerHTML.split('.');
                    let tableDate = new Date(year, month, day);
                    if (maxDate == undefined) {
                        maxDate = tableDate.getTime();
                        [tableDay, tableMonth, tableYear] = [day, month, year];
                    } else if (maxDate < tableDate.getTime()) {
                        maxDate = tableDate.getTime();
                        [tableDay, tableMonth, tableYear] = [day, month, year];
                    }
                }
                return `${tableYear}-${tableMonth}-${tableDay}`;
            }
        };

        pickers.setDefaultDates();
        pickers.activate();
    })();
})();
