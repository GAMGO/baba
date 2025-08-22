const reply = document.getElementById("replyList");
console.log(username, mref);
getClist();

//댓글기능활성화 및 로그인검증
if (username) {
  document.getElementById("btnSave").addEventListener("click", commentReply());
} else {
  document.getElementById("btnLogin").addEventListener("click", function () {
    location.href = "/login";
  });
}

//댓글등록
function commentReply() {
  const url = `/api/comments`;
  const newReply = {
    mref: mref,
    writer: username,
    content: document.getElementById("content").value,
  };
  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json;charset=UTF-8" },
    body: JSON.stringify(newReply),
  };

  fetch(url, option)
    .then((response) => {
      return response.json()
    })
    .then((data) => {
      if (data.success === 1) {
        document.getElementById("content").value = "";
      }
    })
    .then(() => getClist())
    .catch((err) => {
      console.error(err);
    })
}

//댓삭
reply.addEventListener("click", function (e) {
  if (e.target.tagName === "I") {
    const idx = e.target.getAttribute("data-num");
    if (confirm("덧글을 삭제하시겠습니까?")) {
      commentDelete(idx);
    }
  }
});
function commentDelete() {
  const url = `/api/comments/${mref}/${idx}`;
  const option = { method: "DELETE" };
  fetch(url, option)
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      if (data.success === 1) {
        alert("덧글을 삭제하였습니다.");
      }
    })
    .then(() => getClist())
    .catch((err) => {
      console.error("error : ".err);
    });
}

//댓글 목록 불러오기
function getClist() {
  const url = `/api/comments/${mref}`;
  fetch(url)
    .then((response) => {
      console.log("response : ", response);
      return response.json();
    })
    .then((data) => {
      console.log("data : ", data);
      printList(data);
    })
    .catch((err) => {
      console.error("error : ", err);
    });
}

//댓글 목록 출력하기
function printList(list) {
  reply.innerHTML = " ";
  if (list && list.length > 0) {
    list.forEach((dto) => {
      const li = document.createElement("li");
      str = `
    					<li class="list-group-item d-flex">
							<span class="col-5 myfc-1">${dto.writer}</span>
							<span class="col-6">${dto.regDate}</span>`;
      if (dto.writer == username) {
        str += `<span class="col-1">
							<i class="bi bi-trash" data-num="${dto.idx}"></i>
							</span>`;
      }
      str += `</li>
				<li class="list-group-item d-flex">
				<textarea class="form-control myfs-9" disabled>${dto.content}</textarea>
				</li>`;
      li.innerHTML = str;
      li.style.listStyle = "none";
      reply.appendChild(li);
    });
  } else {
    console.error("Any comments are not exist");
    reply.innerHTML = "작성된 댓글이 없습니다.";
  }
}
