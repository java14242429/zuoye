package com.briup.zuoye;

import java.util.Scanner;
/**
    老师信息管理系统
*/
public class Tms{
	//老师数组，用于保存所有老师的信息
	private Teacher[] teas = new Teacher[3];
	//表示老师数组中真正保存老师的个数
	private int index = 0;
	/**
	    查找所有老师的信息
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
	    录入老师的信息
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
		    //数组扩展！6
			Teacher[] demo = new Teacher[teas.length + 3];
            System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index] = tea;
		index++;
	}

	/**
	    通过id删除该老师的信息
		@author lisi
		@param 老师id
	*/
	public void deleteById(long id){
	    int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
		    teas[i] = teas[i+1];
		}
		teas[--index] = null;
	}

	/**
	    更新老师的信息
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
	    通过id查找老师的信息
		@author zhangsan
		@param 要查找的老师的id   :  long
		@return
		        老师，找到了
				null，没找到
	*/
	public Teacher findById(long id){
		int num = findIndexById(id);
	    return num == -1?null:teas[num];
	}

	/**
	    通过id查找老师在数组中的索引
		@author licy
		@param 要查找的老师的id  :   long
		@return
		         -1 ,  没找到了
				 其他正整数  ， 找到了
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

    //菜单
	public void menu(){
	    System.out.println("********老师信息管理系统********");
		System.out.println("* 1. 查看所有老师的信息");
		System.out.println("* 2. 录入老师的信息");
		System.out.println("* 3. 删除老师的信息");
		System.out.println("* 4. 更新老师的信息");
		System.out.println("* 5. 查询老师的信息");
		System.out.println("* help. 帮助");
		System.out.println("* exit. 退出");
		System.out.println("********************************");
	}
	public static void main(String[] args){
	    Tms tms = new Tms();
		tms.menu();
		//扫描标准输入，等待用户的输入
		Scanner sc = new Scanner(System.in);
		while(true){
		    System.out.print("请输入功能编号：");
			//阻塞，直到用户输入回车，将回车前所有用户输入返回
			String line = sc.nextLine();
			switch(line){
			    case "1":
					System.out.println("以下是所有老师的信息：");
				    Teacher[] arr = tms.findAll();
					for(Teacher tea : arr){
					    System.out.println(tea);
					}
					System.out.println("共计 "+tms.index+"人");
					break;
				case "2":
					while(true){
				        System.out.println("请输入老师的信息【id#name#age】或者输入【break】返回上一级");
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
						System.out.println("保存成功！");

				    }
					break;
				case "3":
					while(true){
				        System.out.println("请输入要删除老师的id或者输入【break】返回上一级");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
						    break;
						}
						//1001
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
						    System.out.println("对不起，您要删除的老师不存在！");
							continue;    //结束当前循坏，继续下一次循坏
						}
						tms.deleteById(id);
						System.out.println("删除成功！");

				    }
					break;
				case "4":
					while(true){
				        System.out.println("请输入要更改老师的id或者输入【break】返回上一级");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
						    break;
						}
						//1001
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
						    System.out.println("对不起，您要更改的老师不存在！");
							continue;    //结束当前循坏，继续下一次循坏
						}
						System.out.println("原有老师的信息为：");
						System.out.println(tea);
						System.out.println("请输入要修改的信息【name#age】");
						String teaStr = sc.nextLine();
						String[] teaArr = teaStr.split("#");
						String name = teaArr[0];
						int age = Integer.parseInt(teaArr[1]);
						Teacher newTea = new Teacher(id,name,age);
						tms.update(newTea);
   						System.out.println("修改成功！");

				    }
					break;
				case "5":
					while(true){
				        System.out.println("请输入要查询老师的id或者break返回上一层：");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
						    break;
						}
						long id = Long.parseLong(idStr);
						Teacher tea = tms.findById(id);
						if(tea == null){
						    System.out.println("您要查询的老师不存在！");
                            continue; 
						}
						System.out.println(tea);
				    }
					break;
				case "help":
					tms.menu();
					break;
				case "exit":
					System.out.println("bye bye!欢迎下次再访问！");
				    System.exit(0);
					break;
				default:
					System.out.println("输入错误！");
			}
		}

	}
}