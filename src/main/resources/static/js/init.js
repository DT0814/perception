/**
 * desc: Common Footer Script
 * author: tony dream
 * dateï¼?2014/12/29
 */
$(function() {
	var WebMain = {
		init: function() {
			this.injectCode();
			this.makeNav();
		},
		/**
		 * æä»¶ä»£ç 
		 * @return {[type]} [description]
		 */
		injectCode: function() {
			// google ads
			var buf = [];
			buf.push('<script async src="http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>');
			buf.push('<ins class="adsbygoogle" style="display:inline-block;width:728px;height:90px" data-ad-client="ca-pub-0608155192548477" data-ad-slot="8305246055"></ins>');
			buf.push('<script>(adsbygoogle = window.adsbygoogle || []).push({});</script>');
			$('.footer-banner').html(buf.join(''));

			// å³ä¸æ¬æµ®
			/*buf.push('<script type="text/javascript">var cpro_id = "u2487889";</script><script src="http://cpro.baidustatic.com/cpro/ui/f.js" type="text/javascript"></script>');
			$('body').append(buf.join(''));
			$('.footer-banner').remove();*/

			/*******************************************************/

			var bds = [];
			/*ç¾åº¦ç»è®¡*/
			bds.push('<script>var _hmt = _hmt || [];(function() {var hm = document.createElement("script");hm.src = "//hm.baidu.com/hm.js?3088a94e3b69516f6df1098fc49847c9";var s = document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm, s);})();</script>');
			/*ç¾åº¦åäº«*/
			bds.push('<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"1","bdPos":"right","bdTop":"100"}};with(document)0[(getElementsByTagName("head")[0]||body).appendChild(createElement("script")).src="http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion="+~(-new Date()/36e5)];</script>');
			$('body').append(bds.join(''));
		},
		/**
		 * å¤´é¨å¯¼èª
		 * @return {[type]} [description]
		 */
		makeNav: function() {
			var leftArray = [{
				title: 'ç²¾é?12ä¸ªæ¶å°ç CSS3 ææãéæºç ã?',
				link: 'http://www.yyyweb.com/3103.html'
			}, {
				title: 'Web å¼åä¸­å¾å®ç¨ç10ä¸ªææãéæºç ä¸è½½ã?',
				link: 'http://www.yyyweb.com/350.html'
			}, {
				title: '8ä¸ªåæ²¿ç HTML5 & CSS3 ææãéæºç ä¸è½½ã?',
				link: 'http://www.yyyweb.com/377.html'
			}, {
				title: 'ç½ç«å¼åä¸­å®ç¨ jQuery ææãéæºç ã?',
				link: 'http://www.yyyweb.com/2818.html'
			}, {
				title: '9ä¸ªå¼å¾å­¦ä¹ ç? HTML5 ææãéæºç ã?',
				link: 'http://www.yyyweb.com/3114.html'
			}];
			var rightArray = [{
				title: '10æ¬¾å¾å¥½ç¨ç? jQuery å¾çæ»å¨æä»¶',
				link: 'http://www.yyyweb.com/370.html'
			}, {
				title: 'ç¾ï¼è§å·®æ»å¨å¨å¾çæ»åä¸­çåºç?',
				link: 'http://www.yyyweb.com/237.html'
			}, {
				title: 'è¶ç«çé¡µé¢åæ¢å¨ç»ææãéæºç ã?',
				link: 'http://www.yyyweb.com/demo/page-transitions/'
			}, {
				title: 'å¾é·çç½é¡µè¾¹æ è¿æ¸¡å¨ç»ãéæºç ã?',
				link: 'http://www.yyyweb.com/demo/sidebar-transitions/'
			}, {
				title: 'ä½¿ç¨ CSS3 å®ç° 3D å¾çæ»åææ',
				link: 'http://www.yyyweb.com/demo/slice-box/'
			}];
			var left = leftArray[1],
				right = leftArray[2];
			// var left = leftArray[parseInt(Math.random() * 5)];
			// var right = rightArray[parseInt(Math.random() * 5)];
			$('body').append('<div class="top-banner clearfix"><div class="fl"><a href="' + left.link + '" target="_blank">&lt;&lt;&nbsp;' + left.title + '</a></div><div class="fr"><a href="' + right.link + '" target="_blank">' + right.title + '&nbsp;&gt;&gt;</a></div></div>');
		}
	};
	WebMain.init();
	/*iframeèªéåº*/
	/*$("#iframe-hoster").load(function() {
	  var mainheight = $(this).contents().find("body").height() + 5;
	  $(this).height(mainheight);
	});*/
});