package com.company;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
class p extends JPanel{
    ArrayList<int[]> arr;
    String [] atr,nana;
    ArrayList reject;
    Toolkit body;
    Image image,fruit,bo;
    p (ArrayList<int[]> ar,ArrayList rej){
        arr=ar;
        reject=rej;
        atr= new String[]{"S", "c", "o", "r", "e",":", "" + arr.get(0)[2]};
        body=getToolkit();
        image=body.getImage("src/img/3.PNG");
        fruit=body.getImage("src/img/IMG_0045的副本.PNG");

    }
    public void paintComponent(Graphics g){//绘图
        super.paintComponent(g);
        super.revalidate();

        Graphics2D gggg = (Graphics2D) g;
        setSize(620,628);
        g.drawImage(fruit,arr.get(0)[0],arr.get(0)[1],20,20,this);
        gggg.setColor(Color.BLACK);
        g.setFont(new Font("宋体",Font.BOLD,20));
        image=body.getImage("src/img/"+arr.get(0)[3]+".PNG");
        for (int e=0;e<atr.length;e++){
            if(e==atr.length-1&&(arr.get(0)[0]!=460+e*20 || arr.get(0)[1]!=20 )&&!reject.contains(e)){
                gggg.drawString(arr.get(0)[2]+"", 460 + e * 20, 40);
            }
            else if ((arr.get(0)[0]!=460+e*20 || arr.get(0)[1]!=20 )&&!reject.contains(e)) {
                gggg.drawString(atr[e], 460 + e * 20, 40);
            }

        }
        gggg.drawString(nana[0]+":"+nana[1]+"           MADE BY CAESAR",0,580);
        for (int i=2;i<arr.size();i++) {
            if(i==arr.size()-1){
                g.drawImage(body.getImage("src/img/"+arr.get(i-1)[2]+""+arr.get(i-1)[2]+""+arr.get(i-1)[2]+".PNG"),arr.get(i)[0],arr.get(i)[1],20,20,this);
            }
            else {
                bo = body.getImage("src/img/" + arr.get(i-1)[2]+"" +arr.get(i)[2]+  ".PNG");
                g.drawImage(bo, arr.get(i)[0], arr.get(i)[1], 20, 20, this);
            }
        }
        g.drawImage(image,arr.get(1)[0],arr.get(1)[1],20,20,this);
        gggg.setBackground(Color.ORANGE);
    }
}

class Police extends KeyAdapter {//键盘监控
    int key=9,recode=3,x=20,y=0;
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W&& recode!=1){
            recode=2;
            key=1;
            x=0;
            y=-20;
        }
        else if(e.getKeyCode()==KeyEvent.VK_S&& recode!=2){
            recode=1;
            key=4;
            x=0;
            y=20;
        }
        else if(e.getKeyCode()==KeyEvent.VK_A&& recode!=3){
            recode=4;
            key=6;
            x=-20;
            y=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_D&& recode!=4) {
            recode=3;
            key = 9;
            x=20;
            y=0;
        }
    }
}

public class Main {

    static public DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
// 创建一个DocumentBuilderFactory对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//创建一个DocumentBuilder对象
        DocumentBuilder db = null;

        db = dbf.newDocumentBuilder();


        return db;
        }



    static void create(String na,int score)throws Exception{
        DocumentBuilder db=getDocumentBuilder();
        Document document=db.newDocument();
        Element bookstore=document.createElement("bookStore");

        Element book=document.createElement("book");
        Element name=document.createElement("name");
        bookstore.appendChild(name);
        name.setTextContent(na);

        bookstore.appendChild(book);
        book.setTextContent(score+"");

        document.appendChild(bookstore);

        TransformerFactory tff=TransformerFactory.newInstance();

        Transformer tf=tff.newTransformer();

        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(new File("boos1.xml")));

    }
    static String[] get() throws ParserConfigurationException, IOException, SAXException {
        File f = new File("boos1.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(f);
        NodeList nl = doc.getElementsByTagName("bookStore");
        String name = doc.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
        String phone = doc.getElementsByTagName("book").item(0).getFirstChild().getNodeValue();
        String[] all={name,phone};
        System.out.println(all[1]);
        return all;
    }


    static int[] replace(ArrayList<int[]> arr){
        Boolean ch=false;
        Random ra=new Random();
        int xx=(ra.nextInt(29)+1)*20,yy=(ra.nextInt(29)+1)*20;
        for (int c=1;c<arr.size();c++){
            if (xx==arr.get(c)[0] && yy==arr.get(c)[1]){
                ch=true;
            }
        }
        if (ch==true){
            System.out.println(ch);
            int ar[]=replace(arr);
            return ar;
        }
        else{
            int ar[]={xx,yy};
            return ar;
        }
    }
    static ArrayList<int[]> move(ArrayList<int[]> arr,int x,int y,int key){
        for (int a=0;a<arr.size()-1;a++) {
            if (a==arr.size()-2){
                arr.get(arr.size()-a-1)[0]=arr.get(arr.size()-a-1)[0]+x;
                arr.get(arr.size()-a-1)[1]=arr.get(arr.size()-a-1)[1]+y;
                arr.get(arr.size()-a-1)[2]=key;
            }
            else {
                arr.get(arr.size() - a -1)[0] = arr.get(arr.size() - a - 2)[0];
                arr.get(arr.size() - a-1)[1] = arr.get(arr.size() - a - 2)[1];
                arr.get(arr.size() - a-1)[2] = arr.get(arr.size() - a - 2)[2];
            }
        }
        return arr;
    }
    public static void main(String arg[])throws Exception {
        Main c=new Main();

        String[] get=c.get();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        JFrame w=new JFrame();
        w.setSize(620,628);
        ArrayList  reject = new ArrayList();
        int recode=0;
        Random ran=new Random();
        int fruitX=ran.nextInt(30)*20,fruitY=ran.nextInt(30)*20;
        int x=0,y=0,dir=4;//
        Police pp=new Police();
        ArrayList<int[]>  list=new ArrayList();
        list.add(new int[]{fruitX,fruitY,recode,dir});
        for (int b=0;b<6;b++){
            list.add(new int[]{x-20,y-b,0});
        }
        Boolean run=true;
        p new1=new p(list,reject);
        new1.nana=get;
        w.add(new1);
        w.setVisible(true);
        w.setBackground(Color.ORANGE);
        while (run) {
            int addA=list.get(list.size()-1)[0],addB=list.get(list.size()-1)[1];

            try{
                Thread.sleep(Math.abs(150-list.get(0)[2]));
            }
            catch (InterruptedException e){
                return;
            }
            w.addKeyListener(pp);
            list=move(list,pp.x,pp.y,pp.key);
            list.get(0)[3]=pp.key;
            new1.arr=list;
            w.add(new1);

            if (list.get(1)[0]>600){
                list.get(1)[0]=0;
            }
            else if (list.get(1)[0]<=-20){
                list.get(1)[0]=600;
            }
            if (list.get(1)[1]<0){
                list.get(1)[1]=600;
            }
            else if (list.get(1)[1]>=600){
                list.get(1)[1]=0;
            }
            if (list.get(0)[0]==list.get(1)[0] && list.get(0)[1]==list.get(1)[1]){
                int[] ppp = replace(list);
                list.get(0)[0]= ppp[0];
                list.get(0)[1]=ppp[1];
                list.get(0)[2]++;
                list.add(new int[]{addA,addB,0});
            }
            if (!(reject.contains(0) &&reject.contains(1) && reject.contains(2) && reject.contains(3) && reject.contains(4) && reject.contains(5)&& reject.contains(6)) ){
                for (int f=0;f<7;f++) {
                    if (list.get(1)[0] == 460 + 20 * f && list.get(1)[1] == 20&&!(reject.contains(f))) {
                        reject.add(f);
                        list.get(0)[2]++;
                        list.add(new int[]{addA, addB,0});

                    }
                }
            }
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (int d=2;d<list.size();d++){
                if (list.get(d)[0]==list.get(1)[0]&&list.get(d)[1]==list.get(1)[1]) {
                    run = false;
                    System.out.println(list.get(0)[2]);
                }
            }
        }
        System.out.println(Integer.parseInt(get[1]));
        if (list.get(0)[2]>=Integer.parseInt(get[1])) {
            JTextField new2=new JTextField(10);
            JButton new3=new JButton("确认");
            new2.setFont(new Font(null, Font.PLAIN, 20));
            ArrayList<int[]> finalList = list;

            new3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        c.create(new2.getText(), finalList.get(0)[2]);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    System.out.print("1");
                }
            });
            new1.add(new2);
            new1.add(new3);
        }


    }
}
