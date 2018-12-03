$(function() {
	ask();
})

function ask(){
	$.ajax({
		url:"askPassActivities",
		success:function(result){
		    window.location=("getPassActivities")
		}
	})
}
