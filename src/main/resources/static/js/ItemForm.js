var filesArray=[];
var finalFilesArray=[];
var fileAvailable='';
function appendTags() {
    if ($('#addTag').val() != "") {
        var tagElement = "<div class='input-group m-1'><span class='input-group-text tagsCustomClass'>" + $('#addTag').val() + "</span><div class='input-group-append'><button onclick='deleteTag(event)' class='btn btn-danger'><i class='fa fa-times-circle' aria-hidden='true'></i></button></div></div>";
        var parentTagElement = document.getElementById('taglabels');
        $('#taglabels').append(tagElement);
        $('#addTag').val("");
        $('#finalTags').val(getFinalTags());
    }
};
function appendFileAvailable(singleFile){
     fileAvailable += ','+ singleFile.name;
     finalFilesArray.push(singleFile);
};
function generateImages() {
$( ".imagesForDisplay" ).remove();
iterateImageGeneration();
};
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
function deleteImageButton(event){
    var index = parseInt(event.target.parentElement.id.substring('delButton'.length));
    finalFilesArray.splice(index, 1);
    generateImages();
    $('.custom-file-label').html("Choose Files");
}

$(document).ready(function () {
    $('#priorityLabel').text("Priority : " + $('#customRange3').val());
    $("#customRange3").change(function () {
        $('#priorityLabel').text("Priority : " + $('#customRange3').val());

    });
    $("#addTag").keypress(function (e) {
        if (e.keyCode === 13) {
            appendTags();
        }
    });
    $('.custom-file-input').on('change', function() {
      var temp1 = $('.custom-file-input').get(0).files;
      filesArray.push(temp1);
      //Update FileNames included
      fileAvailable='';
      finalFilesArray=[];
      for(var firstIndex=0;firstIndex<filesArray.length;firstIndex++){
          var secondIndex = 0;
          if(firstIndex == 0){
            fileAvailable += filesArray[firstIndex][0].name;
            finalFilesArray.push(filesArray[firstIndex][0]);
            while(typeof filesArray[firstIndex][++secondIndex] !== 'undefined'){
                appendFileAvailable(filesArray[firstIndex][secondIndex]);
            }
          } else {
            appendFileAvailable(filesArray[firstIndex][0]);
            while(typeof filesArray[firstIndex][++secondIndex] !== 'undefined'){
                appendFileAvailable(filesArray[firstIndex][secondIndex]);
            }
          }
      };
      $(this).siblings('.custom-file-label').addClass('selected').html(fileAvailable);
      generateImages();
    });
});