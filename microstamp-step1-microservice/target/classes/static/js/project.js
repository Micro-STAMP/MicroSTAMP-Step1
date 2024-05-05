var projectSelected;
var projectToBeDeleted;

var actual_modal; // 0 -> addProject 1 -> editProject //control the restrictions return

$(window).ready(function () {
    var user_id = document.getElementById("user_id").innerText;
    $.ajax({
        "type": 'get',
        "url": '/projects/user/' + user_id,
        "dataType": "json",
        "success": function (data) {
            $.each(data, function (idx, obj) {
                $("#projectTable").append("<tr data-tt-id=\"" + obj.id + "\" data-tt-parent-id=\"" + obj.father + "\"><td>" + obj.name + "</td><td><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#editProjectModal' onclick = loadEditProject(this.id) type='button' id=\"" + obj.id + "\"><span class='fa fa-pencil' aria-hidden='true'></span></button><button style='cursor: pointer; border-radius: 5px;' data-toggle='modal' data-target='#confirmProjectDeleteModal' type='button' id=\"" + obj.id + "\" onclick = loadProjectToBeDeleted(this.id)><span class='fa fa-trash'></span></button><button style='cursor: pointer; border-radius: 5px;' type='button' id=\"" + obj.id + "\" onclick=location.href=\"" + obj.id + "\"><span class='fa fa-search' aria-hidden='true'></span></button></td></tr>");
            });
            $("#projectTable").treetable({
                expandable: true,
                initialState: "expanded",
                clickableNodeNames: true,
                indent: 30
            });
        }
    });
});

function addProject() {
    var project = {
        name: $("#project-name").val(),
        description: "",//$("#project-description").val(),
        url: null,
        type: null,
        userId: document.getElementById("user_id").innerText,
    }

    $('#target').html('sending..');

    if($("#project-name").val() == ""){
        $("#addProjectModal").modal("hide");
        actual_modal = 0;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/projects',
            type: 'post',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(project)
        });
    }
}

function loadEditProject(id){
    projectSelected = id;
    $.ajax({
        url: '/projects/'+ id,
        type: 'get',
        success: function (data) {
            $("#project-edit-name").val(data.name);
            //$("#project-edit-description").val(data.description);
        },
    });
}

function editProject() {
    var project = {
        name: $("#project-edit-name").val(),
        description: "",//$("#project-edit-description").val(),
    }

    $('#target').html('sending..');

    if($("#project-edit-name").val() == ""){
        $("#editProjectModal").modal("hide");
        actual_modal = 1;
        $("#namelessRestrictionModal").modal("show");
    }else{
        $.ajax({
            url: '/projects/' + projectSelected,
            type: 'put',
            dataType: 'text',
            contentType: 'application/json',
            success: function (data) {
                location.reload();
            },
            data: JSON.stringify(project)
        });
    }
}

function loadProjectToBeDeleted(id){
    projectToBeDeleted = id;
    $.ajax({
        url: '/projects/'+ id,
        type: 'get',
        success: function (data) {
             $("#project_delete_name").text(data.name);
        },
    });
}

function deleteProject(){
    $.ajax({
        url: '/projects/'+ projectToBeDeleted,
        type: 'delete',
        success: function (data) {
            location.reload();
        },
    });
}

function returnNamelessRestriction(){
    $("#namelessRestrictionModal").modal("hide");
    if(actual_modal == 0)
        $("#addProjectModal").modal("show");
    else
        $("#editProjectModal").modal("show");
}