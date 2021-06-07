var index = {
    init : function() {
        var _this = this;
        var saveBtn = document.querySelector("#btn-save");
        var updateBtn = document.querySelector("#btn-update");
        var deleteBtn = document.querySelector("#btn-delete");

        if (saveBtn !== null) {
            saveBtn.addEventListener('click', function() {
                _this.save();
            });
        }
        if (updateBtn !== null) {
            updateBtn.addEventListener('click', function() {
                _this.update();
            })
        }
        if (deleteBtn !== null) {
            deleteBtn.addEventListener('click', function() {
                _this.delete();
            })
        }
    },
    save : function() {
        var form = document.querySelector("form");
        var title = form.querySelector("#title");
        var author = form.querySelector("#author");
        var content = form.querySelector("#content");
        var data = {
            title: title.value,
            author: author.value,
            content: author.value
        };
        console.log(data);
        fetch("/api/v1/posts", {
            method: "POST",
            dataType: "json",
            body: JSON.stringify(data),
            headers:{
                'Content-Type': 'application/json'
              }
        }).then(function(res){ return res.json() })
          .then(function(res) {
            alert("글이 등록 되었습니다.");
            window.location.href = "/";
          })
          .catch(function(error) {
            alert(JSON.stringify(error));
          });
    },

    update: function() {
        var form = document.querySelector("form");
        var title = form.querySelector("#title");
        var content = form.querySelector("#content");
        var data = {
            title : title.value,
            content: content.value
        };

        var id = form.querySelector("#id").value;

        fetch(`/api/v1/posts/${id}`, {
            method: "PUT",
            dataType: "json",
            headers: {'Content-Type': 'application/json; charset=utf-8'},
            body: JSON.stringify(data)
        })
        .then(function(res) { return res.json })
        .then(function(res) {
            alert("글이 수정 되었습니다.");
            window.location.href = "/";
        })
        .catch(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function() {
        var form = document.querySelector("form");
        var id = form.querySelector("#id").value;
        console.log(id);
        fetch(`/api/v1/posts/${id}`, {
            method: "DELETE",
            dataType: 'json',
            headers: { 'Content-Type': 'application/json'}
        })
        .then(function(res){ return res.json() })
        .then(function() {
            alert("글이 삭제 되었습니다.");
            window.location.href = "/";
        })
        .catch(function(error) {
            alert(JSON.stringify(error));
        });
    }
};
index.init();