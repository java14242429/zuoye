package com.briup.zuoye;

import java.util.Scanner;
/**
    ��ʦ��Ϣ����ϵͳ
*/
public class Tms{
	//��ʦ���飬���ڱ���������ʦ����Ϣ
	private Teacher[] teas = new Teacher[3];
	//��ʾ��ʦ����������������ʦ�ĸ���
	private int index = 0;
	/**
	    ����������ʦ����Ϣ
		teas = {
	             {1001,terry,23},
	             {1002,larry,25},
				 null
		}
		demo = {
	             {1001,terry,23},
				 {1002,larry,25},
		}
		index = 2
	*/
	public Teacher[] findAll(){
	    Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}

	/**
	    ¼����ʦ����Ϣ
		teas = demo = {
		                {1001,terry,32},
						{1002,terry,32},
						{1003,terry,32},
						null,
						null,
						null
		}
		index = 3
	*/
	public void add(Teacher tea){
		if(index>=teas.length){
		    //������չ��6
			Teacher[] demo = new Teacher[teas.length + 3];
            System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index] = tea;
		index++;
	}

	/**
	    ͨ��idɾ������ʦ����Ϣ
		@author lisi
		@param ��ʦid
	*/
	public void deleteById(long id){
	    int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
		    teas[i] = teas[i+1];
		}
		teas[--index] = null;
	}

	/**
	    ������ʦ����Ϣ
	*/
	public void update(Teacher newTea){
	     for(int i=0;i<index;i++){
		     if(teas[i].getId() == newTea.getId()){
			     teas[i].setName(newTea.getName());
				 teas[i].setAge(newTea.getAge());
			 }
		 }
	}

	/**
	    ͨ��id������ʦ����Ϣ
		@author zhangsan
		@param Ҫ���ҵ���ʦ��id   :  long
		@return
		        ��ʦ���ҵ���
				null��û�ҵ�
	*/
	public Teacher findById(long id){
		int num = findIndexById(id);
	    return num == -1?null:teas[num];
	}

	/**
	    ͨ��id������ʦ�������е�����
		@author licy
		@param Ҫ���ҵ���ʦ��id  :   long
		@return
		         -1 ,  û�ҵ���
				 ����������  �� �ҵ���
	*/
	public int findIndexById(long id){
	    int num = -1;
		for(int i=0;i<index;i++){
		    if(teas[i].getId() == id){
			    num = i;
				break;
			}
		}
		return num;
	}

    //�˵�
	public void menu(){
	    System.out.println("********��ʦ��Ϣ����ϵͳ********");
		System.out.println("* 1. �鿴������ʦ����Ϣ");
		System.out.println("* 2. ¼����ʦ����Ϣ");
		System.out.println("* 3. ɾ����ʦ����Ϣ");
		System.out.println("* 4. ������ʦ����Ϣ");
		System.out.println("* 5. ��ѯ��ʦ����Ϣ");
		System.out.println("* help. ����");
		System.out.println("* exit. �˳�");
		System.out.println("********************************");
	}
	public static void main(String[] args){
	    Tms tms = new Tms();
		tms.menu();
		//ɨ���׼���룬�ȴ��û�������
		Scanner sc = new Scanner(System.in);
		while(true){
		    System.out.print("�����빦�ܱ�ţ�");
			//������ֱ���û�����س������س�ǰ�����û����뷵��
			String line = sc.nextLine();
			switch(line){
			    case "1":
					System.out.println("������������ʦ����Ϣ��");
				    Teacher[] arr = tms.findAll();
					for(Teacher tea : arr){
					    System.out.println(tea);
					}
					System.out.println("���� "+tms.index+"��");
					break;
				case "2":
					while(true){
				        System.out.println("��������ʦ����Ϣ��id#name#age���������롾break��������һ��");
						String teaStr = sc.nextLine();
						if(teaStr.equals("break")){
						    break;
						}
						//1001#terry#23
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);//"1001"
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						Teacher tea = new Teacher(id,name,age);
						tms.add(tea);
						System.out.println("����ɹ���");

				    }
					break;
				case "3":
					while(true){
				        System.out.println("������Ҫɾ����ʦ��id�������롾break��������һ��");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
						    break;
						}
						//1001
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
						    System.out.println("�Բ�����Ҫɾ������ʦ�����ڣ�");
							continue;    //������ǰѭ����������һ��ѭ��
						}
						tms.deleteById(id);
						System.out.println("ɾ���ɹ���");

				    }
					break;
				case "4":
					while(true){
				        System.out.println("������Ҫ������ʦ��id�������롾break��������һ��");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
						    break;
						}
						//1001
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
						    System.out.println("�Բ�����Ҫ���ĵ���ʦ�����ڣ�");
							continue;    //������ǰѭ����������һ��ѭ��
						}
						System.out.println("ԭ����ʦ����ϢΪ��");
						System.out.println(tea);
						System.out.println("������Ҫ�޸ĵ���Ϣ��name#age��");
						String teaStr = sc.nextLine();
						String[] teaArr = teaStr.split("#");
						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);
						Teacher newTea = new Teacher(id,name,age);
						tms.update(newTea);
   						System.out.println("�޸ĳɹ���");

				    }
					break;
				case "5":
					while(true){
				        System.out.println("������Ҫ��ѯ��ʦ��id����break������һ�㣺");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
						    break;
						}
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
						    System.out.println("��Ҫ��ѯ����ʦ�����ڣ�");
                            continue; 
						}
						System.out.println(tea);
				    }
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye!��ӭ�´��ٷ��ʣ�");
				    System.exit(0);
					break;
				default:
					System.out.println("�������");
			}
		}

	}
}