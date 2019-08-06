//package com.article.admin.test;//package com.tcl.community.test;
//
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.FirebaseMessagingException;
//import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.Notification;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class TestMongoDB {
//
//    public static void main(String args[]) throws IOException, FirebaseMessagingException {
//
//
//        FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//
//                //您可以在 Firebase 控制台项目的数据库页面上找到您的数据库名称
//                .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
//                .build();
//
//        FirebaseApp.initializeApp(options);
//
//
//        Message message = Message.builder().setNotification(new Notification("测试推送标题","测试的推送内容")).build();
//
//        String response = FirebaseMessaging.getInstance().send(message);
//
//        System.out.println(response);
//
//    }
//
//}
