docement.addEventListener("DOMContentLoaded",function(){
	const issueDate = document.getElementById("issueDate");
	const expiryDate = document.getElementById("expiryDate");
	
	function updateExpiryMin(){
		expiryDate.min = issueDate.value;
		
		if(expiryDate.value && 
			expiryDate.value < issueDate.value
		){expiryDate.value = ""}
	}
	
	issueDate.addEventListener("change", updateExpiryMin);
	updateExpiryMin();
})