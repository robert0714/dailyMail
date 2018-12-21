package develop.service.maas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.context.junit4.SpringRunner;

import develop.odata.fcm.reqest.FcmRequest; 
import develop.odata.fcm.reqest.Notification;
import develop.odata.fcm.response.FcmResponse; 
 
@RunWith(SpringRunner.class)
@SpringBootTest 
public class FCMServiceTest {
	@Autowired
	protected FCMService service ;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private String title ="Maas 測試訊息（抱歉，打擾了！）";

	private String content = "我們都有問題 都很難入睡 \n" + 
			"我們都有問題 造成很多誤會 \n" + 
			"我們都有問題 圍繞著是非 \n" + 
			"我們都不知道怎麼處理我們的問題 \n" + 
			"我們很像 都說我們沒有問題 \n" + 
			"也都堅持我們不一樣 \n" + 
			"隔著防備 卻不知不覺被影響 \n" + 
			"其實 我只想對你講 \n" + 
			"\n" + 
			"我們都很好 我們都不好 \n" + 
			"那些傷害人的話 就別說了 \n" + 
			"我們都很好 我們都不好 \n" + 
			"我們讓彼此難過 都別爭了 \n" + 
			"因為我們都有問題 \n" + 
			"因為我們都有問題 \n" + 
			"\n" + 
			"我們知道自己弱點 但說得堅強 \n" + 
			"一道道證明題 都解得牽強 \n" + 
			"但為了闡述我的理想 激昂地演講 \n" + 
			"也為了攻破你 用力地回想 \n" + 
			"我們那些很扯的過去 \n" + 
			"成為彼此批判的字句 \n" + 
			"用我的秩序鎮壓 是黨的勝利 \n" + 
			"戰火燒過之後 剩下仿的正義 \n" + 
			"不同的個體 不同的靈魂 \n" + 
			"卻總要對方了解自己 特別是情人 \n" + 
			"我們的爭吵已是不同的新聞 \n" + 
			"我們讓嘴用來傷人 不重視親吻 \n" + 
			"針鋒相對的刺蝟 瀟灑的話是自衛 \n" + 
			"我們的智慧 是沒法為彼此交換位置 \n" + 
			"一直只想對峙 忘了尊嚴和面子是兩回事 \n" + 
			"\n" + 
			"我們都很好 我們都不好 \n" + 
			"那些傷害人的話 就別說了 \n" + 
			"我們都很好 我們都不好 \n" + 
			"我們讓彼此難過 都別爭了 \n" + 
			"因為我們都有問題 \n" + 
			"因為我們都有問題 \n" + 
			"\n" + 
			"是什麼一點一點累積 越來越不能忍耐 \n" + 
			"一點一點消逝 一刻也不能等待 \n" + 
			"越來越沒默契 錯誤的翻譯 \n" + 
			"就連玩笑都被解釋成責怪 \n" + 
			"原本 能笑看對方超越自己底線 \n" + 
			"漸漸 卻像是睫毛掉進眼睛裡面 \n" + 
			"當冷漠不斷擴張在愛恨之間 \n" + 
			"最後 兩個熟識的人就從此失聯 \n" + 
			"所以當我們已經對著不同角落放空 \n" + 
			"我只想讓我們關係善終 \n" + 
			"讓我們不受情緒搧動 \n" + 
			"用理智收起一些放縱 \n" + 
			"即使不能歡送彼此 \n" + 
			"讓我們想起初衷 不是比較 是微笑 \n" + 
			"不是計較著 把什麼賠掉 \n" + 
			"笑著接受 緣份是難以預料的 \n" + 
			"不管被加入還是去掉了 \n" + 
			"\n" + 
			"我們都很好 我們都不好 \n" + 
			"那些傷害人的話 就別說了 \n" + 
			"我們都很好 我們都不好 \n" + 
			"我們讓彼此難過 都別爭了 \n" + 
			"因為我們都有問題 \n" + 
			"因為我們都有問題";
	
	@Test
	@Ignore
	public void testSendMsgFcmRequest() throws Exception {
		FcmRequest job = new FcmRequest();
		Notification notification =new Notification ();
		notification.setTitle(title);
		notification.setBody(content);
		job.setNotification(notification );
		List<String > idList =new ArrayList<String>();
		for(int i=0 ;i<1000;++i ) {
//			idList.add("fGaD-FsEva0:APA91bGusvAoXRgzFJJ3h37K7eDN2nwm35cQkju0X1vx1zwSjcDRsUV-1PT1DHnrx0DBFi50xbAkX1V01MW_z1Z15uEqljze5j-RG-H4WLIyaa5z5l8f6GOClS7PenDqzpX6cYBeFQtcg40hHz-DfPYBi7xmu0fd2A");
//			idList.add("cY8nWe4Tl1Q:APA91bG-5Vd9Pjp_Eso4nxL2IGFzyuhrLFZLJDycc_iTxOG2Fz-cKJXMpJ116IrQqGoqOcM33nfHkEbegvC0t0gPi1fVhJHpq0PdbsvlyEQ-nR4_xaliaCzD9nqd1o_Rur5F1pR9Z_x5yky30-g98fm-WRgnQWUTdg");
			
			String[] phoneIds = new String[] {"ejnYFKUx6Ys:APA91bH3trz4hCrgMSpIR29TbpdyRZ1w4Yn1WsMdHzrfccny1uKvr-mn_mSJ5qhhQH7kqW6eMRb9j6uFWwTGDN9vOKgZjpqD6SkEvqwztnpSzq4KOTCbyRXPSZa1tysQsnkVFaqNjvPV4yljVSAp5O-hMYzSUbZcIQ",//成功範例 
					"dWMWSRWklQg:APA91bGWXiStn3aU33E1YbfpLLzZgJCiVJ4aWkPJksCm7_SCBqo_1FheylJuSd4dvyaQ_Jd125d-B2kFlEjnA6W-1cZSi89OJ_IzdCoux-_laLoccDPeqc2EDmfgg7sWe6lQQcjgJuo-_WcEZd5kMp88A2DhM-96Ew",//成功範例 
					"fFJ27CZErh8:APA91bEQg1nouoErYEBCi0h7FtOnXtws3Zean86cmnsm8kLEZvK1Yt8TWR7svt4YCixnN-PTBcUa88wylv24Y63O_0BOtD6qt8JTQk2scNN5QHT8Wc0fGf1aFwi47DgmpiKijlRztiTa",//成功範例 
					"dzVJ1cXL_w0:APA91bFoK26dFThwzreC5otMmV-fxYC67nOhOaVWB1lLNULbX7k6bYZ1kcs5GwMAa_PRwWPtYCk42wLaxSEwBeLuJGXTFFNNMbDEkueqgd3eWW3EAadUNua8ELmr0Zi-lTi0JCvwDW0iPCIEfYn9AVnW40XBurhgEA",//成功範例 
					"dRFDGTabUR4:APA91bGRfpKgQyJO4tliMa7BgD5jk337cgpZeTBK6sMfMrroMeesfbe9rS1c9m-g8zN4oh3RcqDJn5Jso8r4SFvGga78BXwtXn5kpq6UdNgbXhpmhjIOUtv-9hK331yVcoVBRWt8886xxG8FgiBP7vpnVzW3aCTOtA",//成功範例 
					"fFJ27CZErh8:APA91bEQg1nouoErYEBCi0h7FtOnXtws3Zean86cmnsm8kLEZvK1Yt8TWR7svt4YCixnN-PTBcUa88wylv24Y63O_0BOtD6qt8JTQk2scNN5QHT8Wc0fGf1aFwi47DgmpiKijlRztiTa",//成功範例 
					"fV3GeIGbaJ8:APA91bFJDgUs4_408r58fEe33im06ev7yhDcgnNo3hByo9RQBeaG5VHowy4HQvMR38je4ZKvMwIOZSgUiuPygpqfKe6-9xWEaX5hcdm99_R6xO6hhu04wmDVF3Ht-5EktTXE0P2B7oUb0-m_BCvkI5kMYWyP88AFHw",//成功範例 
					"c3gNtJiqnTc:APA91bFRN-wXDb_YOz2yioFre48RM0wqxrivwBVgN2S6wZGh0OL9uvEtuGiKbrv_OdKPCEO4yTZtlS-MV1OrsQZdviTxrFkxvjhLP9g9neykAGNViKj2nO8Pss-QVKFXfKRzPAxdqzCHY_AdpbB7QhCzCdCYWLLy7Q",//成功範例 
					"eh_w1wnymus:APA91bFOcchlF_EZI0TfVbhLn9iSOVCGTq5XFMRSvoIk9W4vdlI3hKmHuvWvQF99SspNKEDt67zjuSJDEc_qxJLBS0a1kSa0q4WhwipOwcBmfPKGttIH2-1WneLOqfdU5YdvzWuF9oN9qi4hLVixIkl5emVyo0-nlA",//成功範例 
					"cY8nWe4Tl1Q:APA91bG-5Vd9Pjp_Eso4nxL2IGFzyuhrLFZLJDycc_iTxOG2Fz-cKJXMpJ116IrQqGoqOcM33nfHkEbegvC0t0gPi1fVhJHpq0PdbsvlyEQ-nR4_xaliaCzD9nqd1o_Rur5F1pR9Z_x5yky30-g98fm-WRgnQWUTdg",//成功範例 
					"DC24443084E05D3FA589828DF882048E0C20567DCB54E16DFA182936A66A6D28",//成功範例 
					"775D50A86DE6C6FB7B73F0A460602B4C9A28D1422869EB1C7640874FA62CF4A3",//成功範例 
					"cY8nWe4Tl1Q:APA91bG-5Vd9Pjp_Eso4nxL2IGFzyuhrLFZLJDycc_iTxOG2Fz-cKJXMpJ116IrQqGoqOcM33nfHkEbegvC0t0gPi1fVhJHpq0PdbsvlyEQ-nR4_xaliaCzD9nqd1o_Rur5F1pR9Z_x5yky30-g98fm-WRgnQWUTdg",
					"fGaD-FsEva0:APA91bGusvAoXRgzFJJ3h37K7eDN2nwm35cQkju0X1vx1zwSjcDRsUV-1PT1DHnrx0DBFi50xbAkX1V01MW_z1Z15uEqljze5j-RG-H4WLIyaa5z5l8f6GOClS7PenDqzpX6cYBeFQtcg40hHz-DfPYBi7xmu0fd2A",//成功範例 
					"dWMWSRWklQg:APA91bGWXiStn3aU33E1YbfpLLzZgJCiVJ4aWkPJksCm7_SCBqo_1FheylJuSd4dvyaQ_Jd125d-B2kFlEjnA6W-1cZSi89OJ_IzdCoux-_laLoccDPeqc2EDmfgg7sWe6lQQcjgJuo-_WcEZd5kMp88A2DhM-96Ew" //失敗範例 
					
			};
			idList.addAll(Arrays.asList(phoneIds));
		}
//		job.setIds( "ejnYFKUx6Ys:APA91bH3trz4hCrgMSpIR29TbpdyRZ1w4Yn1WsMdHzrfccny1uKvr-mn_mSJ5qhhQH7kqW6eMRb9j6uFWwTGDN9vOKgZjpqD6SkEvqwztnpSzq4KOTCbyRXPSZa1tysQsnkVFaqNjvPV4yljVSAp5O-hMYzSUbZcIQ",//成功範例 
//				"dWMWSRWklQg:APA91bGWXiStn3aU33E1YbfpLLzZgJCiVJ4aWkPJksCm7_SCBqo_1FheylJuSd4dvyaQ_Jd125d-B2kFlEjnA6W-1cZSi89OJ_IzdCoux-_laLoccDPeqc2EDmfgg7sWe6lQQcjgJuo-_WcEZd5kMp88A2DhM-96Ew",//成功範例 
//				"fFJ27CZErh8:APA91bEQg1nouoErYEBCi0h7FtOnXtws3Zean86cmnsm8kLEZvK1Yt8TWR7svt4YCixnN-PTBcUa88wylv24Y63O_0BOtD6qt8JTQk2scNN5QHT8Wc0fGf1aFwi47DgmpiKijlRztiTa",//成功範例 
//				"dzVJ1cXL_w0:APA91bFoK26dFThwzreC5otMmV-fxYC67nOhOaVWB1lLNULbX7k6bYZ1kcs5GwMAa_PRwWPtYCk42wLaxSEwBeLuJGXTFFNNMbDEkueqgd3eWW3EAadUNua8ELmr0Zi-lTi0JCvwDW0iPCIEfYn9AVnW40XBurhgEA",//成功範例 
//				"dRFDGTabUR4:APA91bGRfpKgQyJO4tliMa7BgD5jk337cgpZeTBK6sMfMrroMeesfbe9rS1c9m-g8zN4oh3RcqDJn5Jso8r4SFvGga78BXwtXn5kpq6UdNgbXhpmhjIOUtv-9hK331yVcoVBRWt8886xxG8FgiBP7vpnVzW3aCTOtA",//成功範例 
//				"fFJ27CZErh8:APA91bEQg1nouoErYEBCi0h7FtOnXtws3Zean86cmnsm8kLEZvK1Yt8TWR7svt4YCixnN-PTBcUa88wylv24Y63O_0BOtD6qt8JTQk2scNN5QHT8Wc0fGf1aFwi47DgmpiKijlRztiTa",//成功範例 
//				"fV3GeIGbaJ8:APA91bFJDgUs4_408r58fEe33im06ev7yhDcgnNo3hByo9RQBeaG5VHowy4HQvMR38je4ZKvMwIOZSgUiuPygpqfKe6-9xWEaX5hcdm99_R6xO6hhu04wmDVF3Ht-5EktTXE0P2B7oUb0-m_BCvkI5kMYWyP88AFHw",//成功範例 
//				"c3gNtJiqnTc:APA91bFRN-wXDb_YOz2yioFre48RM0wqxrivwBVgN2S6wZGh0OL9uvEtuGiKbrv_OdKPCEO4yTZtlS-MV1OrsQZdviTxrFkxvjhLP9g9neykAGNViKj2nO8Pss-QVKFXfKRzPAxdqzCHY_AdpbB7QhCzCdCYWLLy7Q",//成功範例 
//				"eh_w1wnymus:APA91bFOcchlF_EZI0TfVbhLn9iSOVCGTq5XFMRSvoIk9W4vdlI3hKmHuvWvQF99SspNKEDt67zjuSJDEc_qxJLBS0a1kSa0q4WhwipOwcBmfPKGttIH2-1WneLOqfdU5YdvzWuF9oN9qi4hLVixIkl5emVyo0-nlA",//成功範例 
//				"cY8nWe4Tl1Q:APA91bG-5Vd9Pjp_Eso4nxL2IGFzyuhrLFZLJDycc_iTxOG2Fz-cKJXMpJ116IrQqGoqOcM33nfHkEbegvC0t0gPi1fVhJHpq0PdbsvlyEQ-nR4_xaliaCzD9nqd1o_Rur5F1pR9Z_x5yky30-g98fm-WRgnQWUTdg",//成功範例 
//				"DC24443084E05D3FA589828DF882048E0C20567DCB54E16DFA182936A66A6D28",//成功範例 
//				"775D50A86DE6C6FB7B73F0A460602B4C9A28D1422869EB1C7640874FA62CF4A3",//成功範例 
//				"cY8nWe4Tl1Q:APA91bG-5Vd9Pjp_Eso4nxL2IGFzyuhrLFZLJDycc_iTxOG2Fz-cKJXMpJ116IrQqGoqOcM33nfHkEbegvC0t0gPi1fVhJHpq0PdbsvlyEQ-nR4_xaliaCzD9nqd1o_Rur5F1pR9Z_x5yky30-g98fm-WRgnQWUTdg",
//				"fGaD-FsEva0:APA91bGusvAoXRgzFJJ3h37K7eDN2nwm35cQkju0X1vx1zwSjcDRsUV-1PT1DHnrx0DBFi50xbAkX1V01MW_z1Z15uEqljze5j-RG-H4WLIyaa5z5l8f6GOClS7PenDqzpX6cYBeFQtcg40hHz-DfPYBi7xmu0fd2A",//成功範例 
//				"dWMWSRWklQg:APA91bGWXiStn3aU33E1YbfpLLzZgJCiVJ4aWkPJksCm7_SCBqo_1FheylJuSd4dvyaQ_Jd125d-B2kFlEjnA6W-1cZSi89OJ_IzdCoux-_laLoccDPeqc2EDmfgg7sWe6lQQcjgJuo-_WcEZd5kMp88A2DhM-96Ew" //失敗範例 
//				); 
		job.setIds(idList.toArray(new String[] {}));
		
		FcmResponse response = 	service.sendMsg(job);
//		Thread.sleep(5000l);
//		System.out.println(response);;
		
	}
	@Test
//	@Ignore
	public void testSendMsgFcmForLoopRequest() throws Exception {
		for (int i = 0; i < 1; ++i) {
			FcmRequest job = new FcmRequest();
			Notification notification = new Notification();
			notification.setTitle(i + title);
			notification.setBody(content);
			job.setNotification(notification);
			 
			job.setIds(new String[] {
					"cBABLYIO_HI:APA91bGcZqpo-J5f9v6oY990eajRNVQdfKvEdgDr0Aks0scdJ9VHWbt5sM2oomiuofHJupLkDXNNLk1JYLt6rQHl34WMbfyZJEf7qY8CXA3f60l_YxFAxy7P8JBUXmjAZPORaFLbfJIR" });

			FcmResponse response = service.sendMsg(job);
		}
		
	}
}
