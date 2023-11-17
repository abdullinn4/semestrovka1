const createForum = document.getElementById("createForum");
const forumForm = document.getElementById("forumForm");
const createForumButton = document.getElementById("createForumButton");
const cancelForum = document.getElementById("cancelForum");

createForum.addEventListener("click",function () {
    forumForm.style.display="block";
});
cancelForum.addEventListener("click",function (event) {
    event.preventDefault()
    forumForm.style.display="none";
});
createForumButton.addEventListener("click",function (){
    window.location.href="forums";
})