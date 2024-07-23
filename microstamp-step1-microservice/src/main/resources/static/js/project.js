var projectSelected;
var projectToBeDeleted;
var lastOpenedModal;

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

    $(".modal").on("show.bs.modal", function (e) {
        if($(this).attr('id') !== "errorModal"){
            lastOpenedModal = $(this).attr('id');
        }
    });

    $("#errorModal").on("hidden.bs.modal", function () {
        if (lastOpenedModal) {
            $("#" + lastOpenedModal).modal("show");
        }
    });
});

function addProject() {
    var project = {
        name: $("#project-name").val(),
        description: "",
        url: null,
        type: null,
        userId: document.getElementById("user_id").innerText,
    }

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

function loadEditProject(id){
    projectSelected = id;
    $.ajax({
        url: '/projects/'+ id,
        type: 'get',
        success: function (data) {
            $("#project-edit-name").val(data.name);
        },
    });
}

function editProject() {
    var project = {
        name: $("#project-edit-name").val(),
        description: "",
    }

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