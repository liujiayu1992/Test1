//  ֧�������  �ȸ�,���,360,ŷ��  
          //navigator.getUserMedia���д����Opera�к�����navigator.getUserMedianow  
          if (navigator.getUserMedia) {  
              navigator.getUserMedia(videoObj, function (stream) {  
                //�ȸ�  
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
            //���  
              navigator.mozGetUserMedia(videoObj, function (stream) {  
                      video.src = window.URL.createObjectURL(stream);15715377670  
                      video.play();  
              }, errBack);  
          }