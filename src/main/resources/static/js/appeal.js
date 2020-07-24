
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
            img.onload = function() {
                URL.revokeObjectURL(this.src);
            }
            li.appendChild(img);
            const info = document.createElement("span");
            info.innerHTML = this.files[i].name;
            li.appendChild(info);
        }
    }
}

// // Стандарный input для файлов
// var fileInput = $('#file-field');
//
// // ul-список, содержащий миниатюрки выбранных файлов
// var imgList = $('ul#img-list');
//
// // Контейнер, куда можно помещать файлы методом drag and drop
// var dropBox = $('#img-container');
//
// // Обработка события выбора файлов в стандартном поле
// fileInput.bind({
//     change: function() {
//         displayFiles(this.files);
//     }
// });
//
// // Обработка событий drag and drop при перетаскивании файлов на элемент dropBox
// dropBox.bind({
//     dragenter: function() {
//         $(this).addClass('highlighted');
//         return false;
//     },
//     dragover: function() {
//         return false;
//     },
//     dragleave: function() {
//         $(this).removeClass('highlighted');
//         return false;
//     },
//     drop: function(e) {
//         var dt = e.originalEvent.dataTransfer;
//         displayFiles(dt.files);
//         return false;
//     }
// });
// function displayFiles(files) {
//     $.each(files, function(i, file) {
//         if (!file.type.match()) {
//             // Отсеиваем не картинки
//             return true;
//         }
//         // Создаем элемент li и помещаем в него название, миниатюру и progress bar,
//         // а также создаем ему свойство file, куда помещаем объект File (при загрузке понадобится)
//         var li = $('<li/>' , file.name).appendTo(imgList);
//         $('<div/>').text(file.name).appendTo(li);
//         var img = $('<img/>').appendTo(li);
//         // $('<div/>').addClass('progress').text('0%').appendTo(li);
//         li.get(0).file = file;
//
//         // Создаем объект FileReader и по завершении чтения файла, отображаем миниатюру и обновляем
//         // инфу обо всех файлах
//         var reader = new FileReader();
//         reader.onload = (function(aImg) {
//             return function(e) {
//               // aImg.attr('src', e.target.result);
//                // aImg.attr('width', 250);
//                 /* ... обновляем инфу о выбранных файлах ... */
//             };
//         })(img);
//
//         reader.readAsDataURL(file);
//     });
// }
// function uploadFile(file, url) {
//
//     var reader = new FileReader();
//
//     reader.onload = function () {
//         var xhr = new XMLHttpRequest();
//
//         xhr.upload.addEventListener("progress", function (e) {
//             if (e.lengthComputable) {
//                 var progress = (e.loaded * 100) / e.total;
//                 /* ... обновляем инфу о процессе загрузки ... */
//             }
//         }, false);
//
//         /* ... можно обрабатывать еще события load и error объекта xhr.upload ... */
//
//         xhr.onreadystatechange = function () {
//             if (this.readyState == 4) {
//                 if (this.status == 200) {
//                     /* ... все ок! смотрим в this.responseText ... */
//                 } else {
//                     /* ... ошибка! ... */
//                 }
//             }
//         };
//     }
// }



// //check if browser supports file api and filereader features
// if (window.File && window.FileReader && window.FileList && window.Blob) {
//
//     function humanFileSize(bytes, si) {
//         var thresh = si ? 1000 : 1024;
//         if(bytes < thresh) return bytes + ' B';
//         var units = si ? ['kB','MB','GB','TB','PB','EB','ZB','YB'] : ['KiB','MiB','GiB','TiB','PiB','EiB','ZiB','YiB'];
//         var u = -1;
//         do {
//             bytes /= thresh;
//             ++u;
//         } while(bytes >= thresh);
//         return bytes.toFixed(1)+' '+units[u];
//     }
//
//
//
//     function renderImage(file){
//         var reader = new FileReader();
//
//             reader.onload = function (event) {
//                 the_url = event.target.result
//
//
//                // $('#preview').html("<img width='300'  src='" + the_url + "' />")
//                 $('#name').html(file.name)
//                 $('#size').html(humanFileSize(file.size, "MB"))
//                 $('#type').html(file.type)
//
//
//
//             }
//
//             reader.readAsDataURL(file);
//
//     }
//
//
//
//
//
//     //watch for change on the
//     $( "#the-photo-file-field" ).change(function() {
//         console.log("photo file has been chosen")
//         //grab the first image in the fileList
//         //in this example we are only loading one file.
//         console.log(this.files[0].size)
//         renderImage(this.files[0])
//
//     });
//
//
//
// } else {
//
//     alert('The File APIs are not fully supported in this browser.');
//
// }
//

