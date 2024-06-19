console.log("main.js!!");

$(document).ready(()=>{
	console.log("Ready!!");
});

$("#my_start").click(()=>{
	console.log("Start!!");

	// Quagga
	Quagga.init({
		inputStream: {
			name : "Live",
			type : "LiveStream",
			target: document.getElementById("my_quagga")
		},
		decoder: {
			readers: ["ean_reader"]
		}
	}, err=>{
		if(err){
			console.log(err);
			return;
		}
		console.log("Initialization finished!!");
		Quagga.start();
	});

	Quagga.onProcessed(result=>{
		if(result == null) return;
		if(typeof(result) != "object") return;
		if(result.boxes == undefined) return;
		const ctx = Quagga.canvas.ctx.overlay;
		const canvas = Quagga.canvas.dom.overlay;
		ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));
		Quagga.ImageDebug.drawPath(result.box, 
			{x: 0, y: 1}, ctx, {color: "blue", lineWidth: 5});
	});

	Quagga.onDetected(result=>{
//		console.log("読み取り結果"+result.codeResult.code);
		$("#my_result").text(result.codeResult.code);
		$("#my_barcode div").barcode(result.codeResult.code, "ean13");
		var barcode = result.codeResult.code
		var jsonqr = {isbn : barcode };
		var isbnlength = barcode.length;
		var isbnc = barcode.indexOf('978');
		console.log(isbnlength);
		console.log(isbnc);
		
		if(barcode.length==13 && barcode.indexOf('978')==0){
			sendQr(jsonqr);
		}else if(barcode.length==13 && barcode.indexOf('979')==0){
			sendQr(jsonqr);
		}else if(barcode.length==10){
			sendQr(jsonqr);
		}
//		console.log("送信します:"+ jsonqr)
	});
	
	
});

function sendQr(jsonqr){
	$.ajax({
		type:'get',
		url:'GetQrServlet',
		data:jsonqr,
		async:true,
		success:function(data){
//			console.log("送信しました:" + jsonqr);
			var response1 = JSON.parse(data);
			var url = response1.url;
			if(url.includes('SearchBookServlet')){
				console.log(url);
			window.location.href = url;
				
			}
		}
	});
}


$("#my_stop").click(()=>{
	console.log("Stop!!");
	Quagga.stop();
});