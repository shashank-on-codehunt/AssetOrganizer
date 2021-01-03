var finalFilesArray=[]; //final Files Array which would be used for deployment

$(document).ready(function () {
    // File Upload Related Logic Start
    $('.progress').hide()
    //Upload image button onclick , currently not in use
    $("#uploadButton").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        uploadButtonClick();
    });
    $("#submit").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        uploadForm(event);
    });
    $('.custom-file-input').on('change', function() {
     $('#InfoMessage').text("")
      var temp1 = $('.custom-file-input').get(0).files;
      var index=0;
      while(typeof temp1[index] !== 'undefined'){
          if(finalFilesArray.length === 4){
            $('#InfoMessage').text("You can't choose more than 4 Images for an Item")
            break
          }
          finalFilesArray.push(temp1[index]);
          index++;
      }
      updateCurrentlyUploadedImagesName();
      generateImages();
    });
    // File Upload Related Logic Stop

    // Priority Label update  Start
    $('#priorityLabel').text("Priority : " + $('#customRange3').val());
    $("#customRange3").change(function () {
        $('#priorityLabel').text("Priority : " + $('#customRange3').val());

    });
    // Priority Label update  Stop

    // Update Tag Update Start
    $("#addTag").keypress(function (e) {
        if (e.keyCode === 13) {
            appendTags();
        }
    });
    // Update Tag Update Stop
});

// Update Tag Update Start
function appendTags() {
    if ($('#addTag').val() != "") {
        var tagElement = "<div class='input-group m-1'><span class='input-group-text tagsCustomClass'>" + $('#addTag').val() + "</span><div class='input-group-append'><button onclick='deleteTag(event)' class='btn btn-danger'><i class='fa fa-times-circle' aria-hidden='true'></i></button></div></div>";
        var parentTagElement = document.getElementById('taglabels');
        $('#taglabels').append(tagElement);
        $('#addTag').val("");
        $('#finalTags').val(getFinalTags());
    }
};

// Update Tag Update Start
function deleteTag(event) {
    var toDelElement = event.target;
    if (event.target.onclick == null) {
        toDelElement = event.target.parentElement;
    }
    toDelElement.parentElement.parentElement.remove();
}
function getFinalTags() {
    var tagList = '';
    for (var i = 0; i < $('.tagsCustomClass').length; i++) {
        tagList = tagList + $('.tagsCustomClass')[i].innerHTML + ';';
    }
    return tagList;
}
// Update Tag Update Stop

// File Upload Related Logic Start

function updateCurrentlyUploadedImagesName(){
    var fileAvailable='';
    var index=0;
    while(typeof finalFilesArray[index] !== 'undefined'){
        fileAvailable += (index == 0)?finalFilesArray[index].name:','+ finalFilesArray[index].name;
        index++;
    }
    $('.custom-file-label').html(fileAvailable);
}
// onclick event for all image delete buttons
function deleteImageButton(event){
    var index = parseInt(event.target.parentElement.id.substring('delButton'.length));
    finalFilesArray.splice(index, 1);
    generateImages();
    updateCurrentlyUploadedImagesName();
}
function generateImages() {
    $( ".imagesForDisplay" ).remove();
    iterateImageGeneration();
    $('#files').val(finalFilesArray);
};
//Helper method to generate Images
async function iterateImageGeneration(){
    for(var imageNumber=0;imageNumber<finalFilesArray.length;imageNumber++){
        var gid = "#image"+imageNumber+"";
        var columnDivider = 12/finalFilesArray.length;
        var imageElement ="<div class='imagesForDisplay col-md-"+columnDivider+" my-1'><div class='thumbnail'><button id='delButton"+imageNumber+"' class='btn btn-sm btn-danger deleteButton' onclick='deleteImageButton(event)' style='position:absolute;'><i class='fa fa-times-circle' aria-hidden='true'></i></button><img style='height: 200px;' id='image"+imageNumber+"' src='' class='mx-auto d-block img-fluid rounded' alt='Lights'></div></div>";
        $('#imageContainer').append(imageElement);
        const response = await updateImagesToTagSrc(imageNumber);
        console.log("Response Value :"+response)
    }
}
//Helper method to generate Images
function updateImagesToTagSrc(imageNumber){
    return new Promise((resolve,reject) => {
        var reader = new FileReader();
        var gid = "#image"+imageNumber+"";
        reader.readAsDataURL(finalFilesArray[imageNumber]);
        reader.onload = function(evt) {
            // file is loaded
            result = evt.target.result;
            $(gid).attr('src', result);
            resolve(imageNumber+" completed");
            if(1 === 2){
                reject("Failure")
            }
        };
    })
}
//Ajax Call for Document Upload (if you want this separate)
//Currently not being used
function uploadForm(event){
    event.preventDefault();
    var form = $('form')[0];
    var data = new FormData(form);
    for(var fileCounter=0;fileCounter<finalFilesArray.length;fileCounter++){
        data.append("files",finalFilesArray[fileCounter])
    }
    $('.progress').hide()
     $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/item",
            data: data,
            xhr: function() {
                var xhr = $.ajaxSettings.xhr();
                xhr.upload.onprogress = function(e) {
                    var percentage = Math.floor(e.loaded / e.total *100);
                    $('#imageUploadProgress').attr("style","width: "+percentage+"%")
                    $('#imageUploadProgress').attr("aria-valuenow",""+percentage)
                };
                return xhr;
            },
            //http://api.jquery.com/jQuery.ajax/
            //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                window.location.href = '/'
//                $("#result").text(data);
                console.log("SUCCESS : ", data);
//                $("#btnSubmit").prop("disabled", false);
                $('.progress').hide()
                $('#imageUploadProgress').attr("style","width: 0%")
                $('#imageUploadProgress').attr("aria-valuenow",""+0)
            },
            error: function (e) {

//                $("#result").text(e.responseText);
                console.log("ERROR : ", e);
//                $("#btnSubmit").prop("disabled", false);
                $('#InfoMessage').text("Something went wrong while uploading, Try Again")

            }
        });
}
// File Upload Related Logic Stop






