<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/14
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.awt.*"
         import="java.awt.image.BufferedImage"
         import="java.util.*"
         import="javax.imageio.ImageIO"
         pageEncoding="gb2312" %>
<%
    response.setHeader("Cache-Control","no-cache");
    //zai���ڴ��д���ͼ��
    int width = 60, height = 20;
    BufferedImage image = new BufferedImage(width,height,
            BufferedImage.TYPE_INT_RGB);
    //��ȡ����
    Graphics g = image.getGraphics();
    //���ñ���ɫ
    g.setColor(new Color(200,200,200));
    g.fillRect(0,0,width,height);
    //��ȡ�����������֤��(4λ����)
    Random rnd = new Random();
    int randNum = rnd.nextInt(8999) + 1000;
    String randStr = String.valueOf(randNum);
    //����֤�����Session
    session.setAttribute("randStr",randStr);
    //����֤����ʾ��ͼ����
    g.setColor(Color.black);
    g.setFont(new Font("",Font.PLAIN,20));
    g.drawString(randStr, 10, 17);
    //�������100�����ŵ�,ʹͼ���е���֤�벻�ױ���������̽�⵽
    for (int i = 0; i < 100; i++){
        int x = rnd.nextInt(width);
        int y = rnd.nextInt(height);
        g.drawOval(x,y,1,1);
    }
    ImageIO.write(image,"JPEG",response.getOutputStream());
    out.clear();
    out = pageContext.pushBody();
%>
