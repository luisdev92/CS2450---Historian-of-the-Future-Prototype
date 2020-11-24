var IE = (navigator.appName=="Microsoft Internet Explorer");

function submitForm()
{
	for(x=0;x<form_items.length;x++)
	{
		var item = new Object();
		
		if(IE)
			item = document.forms['FORM' + form_items[x]].elements[0];
		else
		{
			var itemform = document.layers[form_items[x]].document.forms;
			if(itemform.length == 0)
				itemform = document.layers[form_items[x]].document.layers[form_items[x]+'m'].document.layers[form_items[x]+'m'].document.forms;
			
			item = itemform[0].elements[0];
		}

		if(item.type == "text" || item.type == "textarea")
		{
			document.forms['finalform'].elements[item.name].value = item.value;
			
			if(IE && item.getAttribute("required") != null && item.value.length == 0)
			{
				alert(form_message);
				return false;
			}
		}
		else if(item.type == "select-one" && item.selectedIndex!=-1)
			document.forms['finalform'].elements[item.name].value = item.options[item.selectedIndex].value;
		else if(item.type == "select-multiple")
		{
			var selOpts = '';
			for(var i=0;i<item.options.length;i++)
			{
				if(item.options[i].selected==true)
					selOpts += item.options[i].value + "; ";
			}
			document.forms['finalform'].elements[item.name].value = selOpts;
		}
		else if(item.type == "checkbox")
			document.forms['finalform'].elements[item.name].value = item.checked?item.value:'';
		else if(item.type == "radio" && item.checked)
			document.forms['finalform'].elements[item.name].value = item.value;
	}
	
	document.forms['finalform'].submit();
	return true;
}

function leaveOthers(fn,nm)
{
	for(x=0;x<form_items.length;x++)
	{
		if(fn != 'FORM' + form_items[x])
		{
		var item = new Object();
				
		if(IE)
			item = document.forms['FORM' + form_items[x]].elements[0];
		else
			item = document.layers[form_items[x]].document.layers[form_items[x]+'m'].document.layers[form_items[x]+'m'].document.forms[0].elements[0];
			
		if(item.type == "radio" && item.name == nm)	
			item.checked = false;
		}
	}
}

function checkValues(message)
{
	for(var x=0;x<document.forms[0].elements.length;x++)
	{
		if(IE && document.forms[0].elements[x].getAttribute("required") != null && document.forms[0].elements[x].value.length == 0)
		{
			alert(message);
			return false;
		}
	}

	return true;	
}

function reset_form()
{
	for(x=0;x<form_items.length;x++)
	{
		var item = new Object();
		
		if(IE)
			item = document.forms['FORM' + form_items[x]];
		else
		{
			var itemform = document.layers[form_items[x]].document.forms;
			if(itemform.length == 0)
				itemform = document.layers[form_items[x]].document.layers[form_items[x]+'m'].document.layers[form_items[x]+'m'].document.forms;
			
			item = itemform[0].elements[0];
		}
		
		item.reset();
	}
}

function transitions_netscape()
{
	for(var x=0;x<slides[0].elems.length;x++)
	{	
		id = slides[0].elems[x].id;
		document[id].width = document[id].clip.width;
		document[id].height = document[id].clip.height;
	
		filter = slides[0].elems[x].filter.toLowerCase();
		var startTrans = filter.indexOf("transition=");

		if(start!=-1)
		{
			var endTrans = filter.indexOf(")",startTrans);
			document[slides[0].elems[x].id].transition = filter.substring(startTrans+11,endTrans);
			startTrans = filter.indexOf("duration=");
			endTrans = 	filter.indexOf(",",startTrans);
			document[slides[0].elems[x].id].duration = filter.substring(startTrans+9,endTrans);
		}
		
		document[id].aantal = parseInt((document[id].duration*1000)/100);
		document[id].incrX = document[id].width / document[id].aantal;
		document[id].incrY = document[id].height / document[id].aantal;
		document[id].count = 0;
		document[id].status = 0;
		document[id].finishNN = finishNN;
		
		if(parseInt(document[id].transition)==23)
			document[id].transition = parseInt(Math.random()*22);
		else if(parseInt(document[id].transition)>23 || parseInt(document[id].transition)<0)
			document[id].transition = parseInt(Math.random()*22);
		
		document[id].play_transition = catchError;
		
		if(document[id].visibility=='hide')
		{
			switch(parseInt(document[id].transition)) 
			{
				case 0:
				case 1:
				case 2:
				case 3:
				case 12:
					document[id].clip.left = parseInt(document[id].width/2);
					document[id].clip.right = parseInt(document[id].width/2);
					document[id].clip.top = parseInt(document[id].height/2);
					document[id].clip.bottom = parseInt(document[id].height/2);
					document[id].play_transition = transition_boxout;
					break;
				case 4:
					document[id].clip.left = 0;
					document[id].clip.right = parseInt(document[id].width);
					document[id].clip.top = parseInt(document[id].height);
					document[id].clip.bottom = 0;
					document[id].play_transition = transition_wipeup;
					break;
				case 5:
				case 11:								
					document[id].clip.left = 0;
					document[id].clip.right = parseInt(document[id].width);
					document[id].clip.top = 0;
					document[id].clip.bottom = 0;
					document[id].play_transition = transition_wipedown;			
					break;
				case 6:
					document[id].clip.left = 0;
					document[id].clip.right = 0;
					document[id].clip.top = 0;
					document[id].clip.bottom = parseInt(document[id].height);
					document[id].play_transition = transition_wiperight;			
					break;			
				case 7:
					document[id].clip.left = parseInt(document[id].width);
					document[id].clip.right = 0;
					document[id].clip.top = 0;
					document[id].clip.bottom = parseInt(document[id].height);
					document[id].play_transition = transition_wipeleft;			
					break;		
				case 8:	
				case 13:	
				case 14:
				case 22:
					document[id].clip.left = parseInt(document[id].width/2);
					document[id].clip.right = parseInt(document[id].width/2);
					document[id].clip.top = 0;
					document[id].clip.bottom = parseInt(document[id].height);
					document[id].play_transition = transition_verticalout;
					break;
				case 9:
				case 15:	
				case 16:
				case 21:
					document[id].clip.left = 0;
					document[id].clip.right = parseInt(document[id].width);
					document[id].clip.top = parseInt(document[id].height/2);
					document[id].clip.bottom = parseInt(document[id].height/2);
					document[id].play_transition = transition_horizontalout;
					break;
				case 17:
					document[id].clip.left = 0;
					document[id].clip.right = 0;
					document[id].clip.top = 0;
					document[id].clip.bottom = 0;
					document[id].play_transition = transition_leftdown;
					break;
				case 18:
					document[id].clip.left = 0;
					document[id].clip.right = 0;
					document[id].clip.top = parseInt(document[id].height);
					document[id].clip.bottom = 0;
					document[id].play_transition = transition_leftup;
					break;
				case 10:
				case 19:
					document[id].clip.left = parseInt(document[id].width);
					document[id].clip.right = parseInt(document[id].width);
					document[id].clip.top = 0;
					document[id].clip.bottom = 0;
					document[id].play_transition = transition_rightdown;
					break;
				case 20:
					document[id].clip.left = parseInt(document[id].width);
					document[id].clip.right = parseInt(document[id].width);
					document[id].clip.top = parseInt(document[id].height);
					document[id].clip.bottom = 0;
					document[id].play_transition = transition_rightup;
					break;			
			}
		}
	}
}

function startWaves()
{
	if(IE)
	{
		for(var x=0;x<wavesElements.length;x++)
		{
			document.all(wavesElements[x]).wave();
		}
	}
}

var currentSlide = 0;
var oldSlide = null;
var elementName;
var currentElement;
var x=0;

function start()
{
	if(x<slides[currentSlide].elems.length)
	{
		currentElement = slides[currentSlide].elems[x];
		
		if(IE)
		{
	        elementName = document.all(slides[currentSlide].elems[x++].id);
			wait = parseInt(elementName.filters(0).Duration * 1000);
		}
		else
		{
			elementName = document[slides[currentSlide].elems[x].id];
			wait = parseInt(document[slides[currentSlide].elems[x].id].duration*1000);
			x++;
		}
	
		show(wait);
	}
}

function gotoSlide(sl)
{
	window.event.cancelBubble = true;
	oldSlide = currentSlide;
	currentSlide = sl;
	setTimeout('slidestart()',1);	
}

function show(wait)
{
	if(IE)
	{
		elementName.filters(0).Stop();
		elementName.filters(0).Apply();
		elementName.style.visibility = '';
		elementName.filters(0).Play();
	}
	else
	{
		elementName.visibility = 'visible';
		elementName.status = 1;
		elementName.play_transition();
	}
	
	while(currentElement.next == "direct")
	{
		if(x<slides[currentSlide].elems.length)
		{
			currentElement = slides[currentSlide].elems[x];
			
			if(IE)
			{
				elementName = document.all(slides[currentSlide].elems[(x++)].id);
				wait = parseInt(elementName.filters(0).Duration * 1000);
				elementName.filters(0).Apply();
				elementName.style.visibility = '';
				elementName.filters(0).Play();
			}
			else
			{
				elementName = document[slides[currentSlide].elems[(x++)].id];	
				elementName.visibility = 'visible';
				elementName.status = 1;
				elementName.play_transition();				
			}
		}
		else
		{
			break;
		}	
	}
	
	if(currentElement.next == "timer")
	{
		setTimeout('start()', wait + (parseInt(currentElement.wait*1000)));
	}	
	else if(currentElement.next == "direct")
	{
		setTimeout('start()', wait);
	}	
}

function document_onclick()
{
	if(currentElement != null)
	{
		if(IE)
		{
			if(document.all(currentElement.id).filters(0).Status==0 && currentElement.next == "mouseclick")
			{
				start();
			}
		}
		else
		{
			if(document[currentElement.id].status == 2 && currentElement.next == "mouseclick")
			{
				start();			
			}
		}
	}
}

function playSound(id)
{
		sobj = 'document.' + id;
		sdo = 'run()';
		
		if(!IE)
		{
		if(sdo.indexOf('run')!=-1) 
			eval(sobj+ '.play(false)');
		else
			eval(sobj+'.'+sdo);
		}
		else
		{
		if(eval(sobj+".FileName"))
			{
				eval(sobj+ '.stop()');
				eval(sobj+ '.play()');
			}	
		}		
}

function transition_boxout()
{
	if(this.clip.left>0)
	{
		this.clip.left = parseInt((this.width / 2) - (getRound(this,0)/2));
		this.clip.right = parseInt((this.width / 2) + (getRound(this,0)/2));
		this.clip.top = parseInt((this.height / 2) - (getRound(this,1)/2));
		this.clip.bottom = parseInt((this.height / 2) + (getRound(this,1)/2));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_wipeup()
{
	if(this.clip.top>0)
	{
		this.clip.top = parseInt(this.height - getRound(this,1));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_wipedown()
{
	if(this.clip.bottom<parseInt(this.height))
	{
		this.clip.bottom = getRound(this,1);
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_wiperight()
{
	if(this.clip.right<parseInt(this.width))
	{
		this.clip.right = getRound(this,0);
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_wipeleft()
{
	if(this.clip.left>0)
	{
		this.clip.left = parseInt(this.width - getRound(this,0));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_verticalout()
{
	if(this.clip.left>0)
	{
		this.clip.left = parseInt((this.width/2) - (getRound(this,0)/2));
		this.clip.right = parseInt((this.width/2) + (getRound(this,0)/2));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_horizontalout()
{
	if(this.clip.top>0)
	{
		this.clip.top = parseInt((this.height/2) - (getRound(this,1)/2));
		this.clip.bottom = parseInt((this.height/2) + (getRound(this,1)/2));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_leftdown()
{
	if(this.clip.right<this.width)
	{
		this.clip.right = getRound(this,0);
		this.clip.bottom = getRound(this,1);
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_leftup()
{
	if(this.clip.top>0)
	{
		this.clip.right = getRound(this,0);
		this.clip.top = parseInt(this.height - getRound(this,1));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_rightdown()
{
	if(this.clip.left>0)
	{
		this.clip.left = parseInt(this.width - getRound(this,0));
		this.clip.bottom = getRound(this,1);
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function transition_rightup()
{
	if(this.clip.top>0)
	{
		this.clip.left = parseInt(this.width - getRound(this,0));
		this.clip.top = parseInt(this.height - getRound(this,1));
		this.count++;
		setTimeout("document['" + this.name + "'].play_transition()",100);
	}
	else
		this.finishNN();
}

function getRound(id,type)
{
	if(type==0)
		return Math.round(id.count * id.incrX);
	else	
		return Math.round(id.count * id.incrY);
}		

function finishNN()
{
	this.clip.left = 0;
	this.clip.right	= this.width;
	this.clip.top = 0;
	this.clip.bottom = this.height;
	this.status = 2;
}

// marquee.js v1.3 25-4-99 17:03
var UP = 1;
var DOWN = 2;
var LEFT = 4;
var RIGHT = 8;

var SCROLL = 1;
var SLIDE = 2;
var ALTERNATE = 4;

function marqueeObject(dir, pr, ch, tm, ds, sc, lp, dl, s, t)
{
	this.dir = dir;
	this.pr = IE?document.all(pr):document.layers[pr];
	this.ml = IE?document.all(ch):this.pr.document.layers[ch];
	this.dx = 0;
	this.dy = 0;
	this.mlw = IE?this.pr.clientWidth:this.pr.clip.width;
	this.mlh = IE?this.pr.clientHeight:this.pr.clip.height;
	this.ln = ch;
	this.tm = tm;
	this.dl = dl;
	this.sc = sc;
	this.lp = lp;
	this.lpc = 0;
	this.px = 0;
	this.py = 0;
	this.s = s;

	this.resetMarq = resetMarq;
	this.moveMarq = moveMarq;
	this.setClip = setClip;
	this.changeClip = changeClip;
	this.moveAbs = moveAbs;
	this.moveRel = moveRel;

	if(dir&UP)
	{
		this.dy = -ds;
	}
	else if(dir&DOWN)
	{
		this.dy = ds;
	}
	var ratio = this.mlh / this.mlw;
	if(dir&LEFT)
	{
		this.dx = -ds;

		if(dir&(UP|DOWN))
		{
			this.dy *= ratio;
		}
	}
	else if(dir&RIGHT)
	{
		this.dx = ds;
		if(dir&(UP|DOWN))
		{
			this.dy *= ratio;
		}
	}

	this.setClip(0, this.dx!=0?0:this.mlw, this.dy!=0?0:this.mlh, 0);

	if(IE && t!=1)
		this.pr.style.visibility = '';
	else
		this.pr.visibility = 'visible';
}

function setClip(a, b, c, d)
{
	this.ct = a;
	this.cw = b;
	this.ch = c;
	this.cl = d;
	if(IE)
		this.ml.style.clip = "rect(" + a + " " + b + " " + c + " " + d + ")";
	else
	{
		this.ml.clip.top = a;
		this.ml.clip.right = b;
		this.ml.clip.bottom = c;
		this.ml.clip.left = d;
	}
}

function changeClip(a, b, c, d)
{
	this.ct += a;
	this.cw += b;
	this.ch += c;
	this.cl += d;
	this.setClip(this.ct, this.cw, this.ch, this.cl);
}

function moveAbs(x, y)
{
	this.px = x;
	this.py = y;
	if(IE)
	{
		this.ml.style.left = x;
		this.ml.style.top = y;
	}
	else
		this.ml.moveTo(x, y);
}

function moveRel(x, y)
{
	this.px += x;
	this.py += y;
	if(IE)
	{
		this.ml.style.left = this.px;
		this.ml.style.top = this.py;
	}
	else
		this.ml.moveTo(this.px, this.py);
}

function resetMarq()
{
	this.setClip(0, this.dx!=0?0:this.mlw, this.dy!=0?0:this.mlh, 0);
	this.moveAbs(0, 0);
	if(this.dir&LEFT)
	{
		this.moveRel(this.mlw, 0);
	}
	else if(this.dir&RIGHT)
	{
		this.moveRel(-this.mlw, 0);
		this.changeClip(0, this.mlw, 0, this.mlw);
	}
	if(this.dir&UP)
	{
		this.moveRel(0, this.mlh);
	}
	else if(this.dir&DOWN)
	{
		this.moveRel(0, -this.mlh);
		this.changeClip(this.mlh, 0, this.mlh, 0);
	}
	if(this.lp==0 || (this.lp>0 && this.lpc<this.lp-1))
	{
		if(this.lpc>0 && this.sc==ALTERNATE && this.s!="")
			playSound(this.s);
		this.lpc++;
		setTimeout("marquees." + this.ln + ".moveMarq()", this.dl);
	}
	else if(this.lpc<this.lp)
	{
		this.lpc++;
		this.sc = SLIDE;
		setTimeout("marquees." + this.ln + ".moveMarq()", this.dl);
	}
	else
	{
		if(this.s!="")
			playSound(this.s);
		this.moveAbs(0, 0);
		this.setClip(0, this.mlw, this.mlh, 0);
	}
}
function moveMarq()
{
	var r = false, b1 = false, b2 = false;
	if(this.dir&LEFT)
	{
		b1 = (this.px <= -this.mlw);
		b2 = (this.px <= 0);
	}
	else if(this.dir&RIGHT)
	{
		b1 = (this.px >= this.mlw);
		b2 = (this.px >= 0);
	}
	if(this.dir&UP)
	{
		b1 |= (this.py <= -this.mlh);
		b2 |= (this.py <= 0);
	}
	else if(this.dir&DOWN)
	{
		b1 |= (this.py >= this.mlh);
		b2 |= (this.py >= 0);
	}
	if(b1)
	{
		if(this.sc&ALTERNATE)
		{
			this.dx = -this.dx;
			this.dy = -this.dy;
			this.dir += this.dir&LEFT?RIGHT-LEFT:this.dir&RIGHT?LEFT-RIGHT:0;
			this.dir += this.dir&UP?DOWN-UP:this.dir&DOWN?UP-DOWN:0;
		}
		r = true;
	}
	else if(b2)
	{
		if(this.sc&SLIDE)
		r = true;
	else
	{
		if(this.dir&LEFT)
			this.changeClip(0, 0, 0, -this.dx);
		else if(this.dir&RIGHT)
			this.changeClip(0, -this.dx, 0, 0);
		if(this.dir&DOWN)
			this.changeClip(0, 0, -this.dy, 0);
		else if(this.dir&UP)
			this.changeClip(-this.dy, 0, 0, 0);
		}
	}
	else
	{
	if(this.dir&LEFT)
		this.changeClip(0, -this.dx, 0, 0);
	else if(this.dir&RIGHT)
		this.changeClip(0, 0, 0, -this.dx);
	if(this.dir&DOWN)
		this.changeClip(-this.dy, 0, 0, 0);
	else if(this.dir&UP)
		this.changeClip(0, 0, -this.dy, 0);
	}
	if(!r)
	{
		this.moveRel(this.dx, this.dy);
		setTimeout("marquees." + this.ln + ".moveMarq()", this.tm);
	}
	else
		setTimeout("marquees." + this.ln + ".resetMarq()", this.tm);
}

document.onclick  =  document_onclick;
