//  支持浏览器  谷歌,火狐,360,欧朋  
          //navigator.getUserMedia这个写法在Opera中好像是navigator.getUserMedianow  
          if (navigator.getUserMedia) {  
              navigator.getUserMedia(videoObj, function (stream) {  
                //谷歌  
                   MediaStreamTrack=typeof stream.stop==='function'?stream:stream.getTracks()[1];  
                       video.src=(window.URL).createObjectURL(stream);  
                  video.play();  
              }, errBack);  
          } else if (navigator.webkitGetUserMedia) {  
            //360  
              navigator.webkitGetUserMedia(videoObj, function (stream) {  
                  MediaStreamTrack=stream.getTracks()[1];  
                  video.src=(window.webkitURL).createObjectURL(stream);  
                  video.play();  
              }, errBack);  
          } else if (navigator.mozGetUserMedia){  
            //火狐  
              navigator.mozGetUserMedia(videoObj, function (stream) {  
                      video.src = window.URL.createObjectURL(stream);15715377670  
                      video.play();  
              }, errBack);  
          }