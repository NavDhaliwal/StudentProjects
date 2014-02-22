import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.* ;
import java.io.*;
class CardDemoGUI extends JPanel implements MouseListener, MouseMotionListener,ActionListener,ItemListener
{

     static final int IMAGE_WIDTH = 73;
     static final int IMAGE_HEIGHT = 97;
     int initX = 200;
     int initY = 0;
	int flag=0,c1=0,c2=0,c3=0,c4=0,c5=0;
	int showcounter1=0,showcounter2=0,showcounter3=0,showcounter4=0;
	int selected=-1;
     int initX1 = 200 ;
     int initY1 = 570 ;
     int initX2 = 0 ;
     int initY2 = 30 ;
     int initX3 = 900 ;
     int initY3 = 30 ;
     int urturn=1;
    int truthcounter=0;
    int newgamecounter=1;
    int posx2=0,posy2=336+24,add2=13;
    int posx3=336+200,posy3=0,add3=13;
    int posx4=900,posy4=336+24,add4=13;
    int throwcounter=0;
    int z1=0,z2=0,z3=0,z4=0;
    int turn=0,pass=0,passcounter=0;
    int add=13,posx=336+200,posy=570;
    int last=1,des=0;
    int cfirst=0;
    int passpaint2=0,passpaint3=0,passpaint4=0;
	Choice throwas=new Choice();
		JButton btshow=new JButton("Show");
		JButton btpass=new JButton("Pass");
		JButton btthrow=new JButton("Throw");
		Label lthrowas=new Label("Throwas");

	Panel p=new Panel();
	Font f = new Font("Dialog", Font.BOLD|Font.ITALIC, 18);
    private Random rd ;

     Card[] deck  = new Card[52];
     Card[] ndeck = new Card[52] ;
     Card[] player1 = new Card[52] ;
     Card[] player2 = new Card[52] ;
     Card[] player3 = new Card[52] ;
     Card[] player4 = new Card[52] ;
     Card[] thrown = new Card[52] ;
     //For AI purposes
     Map<Integer,Integer> humanHas = new HashMap<Integer,Integer>();
     int humanLied = 0;
     int humanShowTimes = 0;
   //For AI purposes;
	int a[]=new int[52];
	int index1[]=new int[8];
	int index2[]=new int[8];
	int index3[]=new int[8];
	int index4[]=new int[8];
	int showindex[]=new int[8];
    Card currentCard = null;

     char v[]={'a','2','3','4','5','6','7','8','9','t','j','q','k'};
     char s[]={'c','d','h','s'};


	public CardDemoGUI()
    {
		File fi=null ;
		FileInputStream f_in=null;
        saved safe=new saved();
        ////////////////////////////////////deserializing
        try
        {
        // Read from disk using FileInputStream
        fi=new File("myobject.data");
        if(fi.exists())
		f_in=new FileInputStream(fi);

		// Read object using ObjectInputStream
		if(fi.exists()){
			ObjectInputStream obj_in =
			new ObjectInputStream (f_in);

		// Read an object
		Object obj = obj_in.readObject();

		if (obj instanceof saved)
		{
			// Cast object to a saved class
			safe = (saved)obj;

		}


		}
		//System.out.println("found");
		}//try ends
	catch(Exception io)
	{
		System.out.println("not found"+io);
	}
		if(fi.exists())
		{
        initX=safe.initX;
					initX1=safe.initX1;
					initX2=safe.initX2;
					initX3=safe.initX3;
					initY=safe.initY;
					initY1=safe.initY1;
					initY2=safe.initY2;
					initY3=safe.initY3;
					urturn=safe.urturn;
					         truthcounter=safe.truthcounter;
					         newgamecounter=safe.newgamecounter;
					         posx2=safe.posx2;
					         posy2=safe.posy2;
					         add2=safe.add2;
					         posx3=safe.posx3;
					         posy3=safe.posy3;
					         add3=safe.add3;
					         posx4=safe.posx4;
					         posy4=safe.posy4;
					         add4=safe.add4;
					         throwcounter=safe.throwcounter;
					         z1=safe.z1;z2=safe.z2;z3=safe.z3;z4=safe.z4;
					         turn=safe.turn;
					         pass=safe.pass;
					         passcounter=safe.passcounter;
					         add=safe.add;posx=safe.posx;posy=safe.posy;
					         last=safe.last;
					         des=safe.des;
					         cfirst=safe.cfirst;
		    passpaint2=safe.passpaint2;
		    passpaint3=safe.passpaint3;
		    passpaint4=safe.passpaint4;
		    currentCard=safe.currentCard;
		    for(int i=0;i<52;i++)
		    {
				deck[i]=safe.deck[i];
				ndeck[i]=safe.ndeck[i];
				player1[i]=safe.player1[i];
				player2[i]=safe.player2[i];
				player3[i]=safe.player3[i];
				player4[i]=safe.player4[i];
				thrown[i]=safe.thrown[i];
				a[i]=safe.a[i];

			}
			for(int i=0;i<8;i++)
			{
				index1[i]=safe.index1[i];
				index2[i]=safe.index2[i];
				index3[i]=safe.index3[i];
				index4[i]=safe.index4[i];
				showindex[i]=safe.showindex[i];
			}
		}
        ////////////////////////////////////deserializing ends
		if(newgamecounter==1)
		{
		for(int i=0;i<4;i++)
		{
			index1[i]=-1;
			index2[i]=-1;
			index3[i]=-1;
			index4[i]=-1;
			z1=0;z2=0;z3=0;z4=0;
		}
		}
				throwas.add("aces");
				throwas.add("2");
				throwas.add("3");
				throwas.add("4");
				throwas.add("5");
				throwas.add("6");
				throwas.add("7");
				throwas.add("8");
				throwas.add("9");
				throwas.add("10");
				throwas.add("jacks");
				throwas.add("queens");
				throwas.add("kings");
		setFont(f);
		setForeground(Color.black);
		p.add(lthrowas);
		p.add(throwas);
		p.add(btthrow);
	    p.add(btshow);
		p.add(btpass);

		setLayout(new BorderLayout());
		add(p,BorderLayout.SOUTH);
        if(!fi.exists()||newgamecounter==1)
        {
        for(int i=0;i<4;i++)
		{
			des=0;
		    for(int j=0;j<13;j++)
		    {
			    ImageIcon img = new ImageIcon("cards/"+v[j]+s[i]+".gif") ;
			    			    initX += img.getIconWidth() / 2 ;

		        deck[i*13+j] = new Card(img, initX, initY,des) ;
		        des=des+1;

		    }
	    }
	    }
        rd = new Random() ;
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.green);
        addMouseListener(this);
        addMouseMotionListener(this);
        btthrow.addActionListener(this);
        btpass.addActionListener(this);
        btshow.addActionListener(this);
        throwas.addItemListener(this);



	if(newgamecounter==1)
        shuffleCards() ;
    }

    public void shuffleCards()
    {


       for (int i=0 ; i < deck.length ; i++)
       {
		    player1[i] = null ;
		    player2[i] = null ;
		     player3[i] = null ;
		    player4[i] = null ;

       }

       for (int i=0 ; i < ndeck.length ; i++)
        {
 		     ndeck[i] = null ;
        }

      int n ;

	   while(true)
	   {
	   	flag=0;
	   n = rd.nextInt(52);
	   for(int j=0;j<c1;j++)
	   {
		   if(n==a[j])
	   		flag=1;
	   }
	   if(flag==0)
	   {
		   a[c1]=n;c1++;
	   }
	   if(c1==52) break;
	   }

	   for(int i=0;i< 52;i++)
	      ndeck[i]= deck[a[i]];

       initX = 200 ;
	   initY = 0 ;
       initX1 = 200 ;
	   initY1 = 570 ;

       for (int i=0,j=0 ; i < ndeck.length/4 ; i++,j++)
       {
		       currentCard = ndeck[i] ;
	           if ( currentCard != null )
	           {
	           	   initX += currentCard.image.getIconWidth() / 3;
			       currentCard.setXY(initX,initY);
			       currentCard.setorigin(initX,initY);
		           player3[i] = currentCard;
		       }

       }
	   for (int i=13,j=0 ; i < ndeck.length/2 ; i++,j++)
	   {

			   currentCard = ndeck[i] ;
	           if ( currentCard != null )
	           {
	           	   initX1 += currentCard.image.getIconWidth() / 3;
			       currentCard.setXY(initX1,initY1) ;
			       currentCard.setorigin(initX1,initY1);
		           player1[j] = currentCard ;
		       }
	   }
	   for (int i=26,j=0 ; i < 39; i++,j++)
	   	   {

	   			   currentCard = ndeck[i] ;
	   	           if ( currentCard != null )
	   	           {
	   	           	   initY2 += currentCard.image.getIconHeight() / 4 ;
	   			       currentCard.setXY(initX2,initY2) ;
	   			       currentCard.setorigin(initX2,initY2);
	   		           player2[j] = currentCard ;
	   		       }
	       }
	   for (int i=39,j=0 ; i < ndeck.length ; i++,j++)
	   	   {

	   			   currentCard = ndeck[i] ;
	   	           if ( currentCard != null )
	   	           {
	   	           	   initY3+= currentCard.image.getIconHeight() / 4 ;
	   			       currentCard.setXY(initX3,initY3) ;
	   			       currentCard.setorigin(initX3,initY3);
	   		           player4[j] = currentCard ;
	   		       }
	       }
    }


    	public void paintComponent(Graphics g)
    	{
         super.paintComponent(g);
         System.out.println("Active threads = " +
		             Thread.activeCount());

         g.fillRect(330,400,350,130);
         if(selected==0)
         g.drawString("ACES",500,350);
         if(selected==1)
         g.drawString("TWOS",500,350);
         if(selected==2)
         g.drawString("THREES",500,350);
         if(selected==3)
         g.drawString("FOURS",500,350);
         if(selected==4)
         g.drawString("FIVES",500,350);
         if(selected==5)
         g.drawString("SIXES",500,350);
         if(selected==6)
         g.drawString("SEVENS",500,350);
         if(selected==7)
         g.drawString("EIGHTS",500,350);
         if(selected==8)
         g.drawString("NINES",500,350);
         if(selected==9)
         g.drawString("TENS",500,350);
         if(selected==10)
         g.drawString("JACKS",500,350);
         if(selected==11)
         g.drawString("QUEENS",500,350);
         if(selected==12)
         g.drawString("KINGS",500,350);
         if(urturn==1)
         {
         g.drawString("YOUR TURN TO START",400,390);
	     urturn=0;
	     }
         int flag=0;
         for(int i=0;i<52;i++)
         {
			 if(player1[i]!=null)
			 {
				 flag=1;
				 break;
			 }
		 }
		 if(flag==0)
		 g.drawString("U r the BluffMaster",400,370);
		 flag=0;
         for(int i=0;i<52;i++)
         {
			 if(player2[i]!=null)
			 {
				 flag=1;
				 break;
			 }
		 }
		 if(flag==0)
		 g.drawString("Player 2 is the BluffMaster",400,370);
		 flag=0;
		          for(int i=0;i<52;i++)
		          {
		 			 if(player3[i]!=null)
		 			 {
		 				 flag=1;
		 				 break;
		 			 }
		 		 }
		 		 if(flag==0)
		 g.drawString("Player 3 is the BluffMaster",400,370);
		 flag=0;
		          for(int i=0;i<52;i++)
		          {
		 			 if(player4[i]!=null)
		 			 {
		 				 flag=1;
		 				 break;
		 			 }
		 		 }
		 		 if(flag==0)
		 g.drawString("Player 4 is the BluffMaster",400,370);
         if(showcounter2==1)
			{
			g.drawString("SHOW",100,300);
			showcounter2=0;
		    }
		    if(showcounter3==1)
						{
						g.drawString("SHOW",500,130);
						showcounter3=0;
		    }
		    if(showcounter4==1)
						{
						g.drawString("SHOW",820,300);
						showcounter4=0;
		    }
		    if(passpaint2==1)
		    {
				g.drawString("PASS",100,300);
				passpaint2=0;
			}
			if(passpaint3==1)
					    {
							g.drawString("PASS",500,130);
							passpaint3=0;
						}
            if(passpaint4==1)
					    {
							g.drawString("PASS",820,300);
							passpaint4=0;
						}

         for (int crd=0; crd<deck.length; crd++)
         {
            Card c = player1[crd];
            if ( c != null)
                c.image.paintIcon(this, g, c.x, c.y);
         }

         for (int crd=0; crd<deck.length; crd++)
		 		 {
		 		     Card c = player2[crd];
		 		     if ( c != null)
		 		        c.back.paintIcon(this, g, c.x, c.y);
		          }
		          for (int crd=0; crd<deck.length; crd++)
		 		 		 {
		 		 		     Card c = player3[crd];
		 		 		     if ( c != null)
		 		 		        c.back.paintIcon(this, g, c.x, c.y);
		          }

		          for (int crd=0; crd<deck.length; crd++)
		 		 		 {

		 		 		     Card c = player4[crd];
		 		 		     if ( c != null)
		 		 		        c.back.paintIcon(this, g, c.x, c.y);
                         }
                         //slowing the showing process
                         if(showcounter1==1&&last==4)
                         {
							 for(int i=0;i<8;i++)
                         {    if(showindex[i]!=-1)
							 {Card c=player4[showindex[i]];c.image.paintIcon(this, g, c.x, c.y);}}
					     showcounter1=0;
					     }
					     if(showcounter1==1&&last==3)
						                          {
													  for(int i=0;i<8;i++)
						                          {    if(showindex[i]!=-1)
													  {Card c=player3[showindex[i]];c.image.paintIcon(this, g, c.x, c.y);}}
						 					     showcounter1=0;
					     }
					     if(showcounter1==1&&last==2)
						                          {
													  for(int i=0;i<8;i++)
						                          {     if(showindex[i]!=-1)
													  {Card c=player2[showindex[i]];c.image.paintIcon(this, g, c.x, c.y);}}
						 					     showcounter1=0;
					     }
					     for(int i=0;i<8;i++)
					     showindex[i]=-1;
                         //




    	}


		 public void mousePressed(MouseEvent e)
    	{
        int x = e.getX();
        int y = e.getY();
        currentCard = null;



        for (int crd = 51; crd >= 0; crd--)
		        {
					if(player1[crd]!=null)
					{
		            Card testCard = player1[crd];

		 			if (x >= testCard.x  &&  x <= (testCard.x + IMAGE_WIDTH/3)
		                    && y >= testCard.y && y <= (testCard.y + IMAGE_HEIGHT))
		            {
		                currentCard = testCard;
		                break;
		            }
				    }
                 }
   		 }


    public void mouseDragged(MouseEvent e)
    {
        if (currentCard != null)
        {
            currentCard.x = e.getX() ;
            currentCard.y = e.getY() ;
            this.repaint();
        }
    }

    public void mouseExited(MouseEvent e)
    {
        currentCard = null;
    }

    public void mouseMoved   (MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseClicked (MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}


    public void itemStateChanged(ItemEvent ie)
    {
		if(selected==-1)
		selected=throwas.getSelectedIndex();
	}

    	public void actionPerformed(ActionEvent ae)
		{
			String str = ae.getActionCommand();
			if(str.equals("Throw") && selected!=-1)
			{
				humanShowTimes--;
				c2=0;z1=0;
				for(int i=0;i<52;i++)
				{
					 if ( player1[i] != null && selected!=-1)
					 {
						if(player1[i].x>=330 && player1[i].x<=680 && player1[i].y>=400 && player1[i].y<=530)
						{
							thrown[throwcounter]=player1[i];
							c2++;
							cfirst++;
							throwcounter++;
							last=1;
                    	    index1[z1]=i;
                    	    z1++;
                    	    pass=0;
						}
				     }
				}

			}

			if(str.equals("Pass") && selected!=-1)
			{
				humanShowTimes--;
				pass++;
				check();

			}

			if(str.equals("Show") && selected!=-1 && urturn!=1)
			{
				humanShowTimes=0;
				showcounter1=1;
				//showing the last cards
				if(last==4)
				{
					for(int i=0;i<z4;i++)
					showindex[i]=index4[i];
				}
				if(last==3)
								{
									for(int i=0;i<z3;i++)
									showindex[i]=index3[i];
				}
				if(last==2)
								{
									for(int i=0;i<z2;i++)
									showindex[i]=index2[i];
				}new Thread(
										  new Runnable() {
											public void run() {


												try{Thread.sleep(1000);}catch(Exception e){}
											}
										  }
					).start();

				//
				this.repaint();
				int tempshow=0;
				if(last==4)
				{
					for(int i=0;i<z4;i++)
					{
						if(player4[index4[i]].des==selected)
						tempshow++;
					}
					if(tempshow==z4)//if player 4 says truth
					{

						humanHas.put(selected, z4);
						int i=0;turn=4;
						while(i!=throwcounter)
					   {
							
							player1[add]=thrown[i];

							player1[add].x=posx;
							player1[add].y=posy;
							i++;add++;posx=posx+20;
				       }

					}
					else
					{
						int i=0;turn=1;
						while(i!=throwcounter)
						{
							player4[add4]=thrown[i];
							player4[add4].x=posx4;
							player4[add4].y=posy4;
							i++;add4++;posy4=posy4+20;
						}
					}
				}



				 tempshow=0;
								if(last==3)
								{
									for(int i=0;i<z3;i++)
									{
										if(player3[index3[i]].des==selected)
										tempshow++;
									}
									if(tempshow==z3)
									{
										humanHas.put(selected, z3);
										int i=0;turn=3;
										while(i!=throwcounter)
									   {
											player1[add]=thrown[i];
											thrown[i].x=posx;
											thrown[i].y=posy;
											i++;add++;posx=posx+20;
								       }

									}
									else
									{
											int i=0;turn=1;
											while(i!=throwcounter)
											{
											player3[add3]=thrown[i];
											player3[add3].x=posx3;
											player3[add3].y=posy3;
											i++;add3++;posx3=posx3+20;
											}
					                  }
				}


				 				tempshow=0;
								if(last==2)
								{
									for(int i=0;i<z2;i++)
									{
										if(player2[index2[i]].des==selected)
										tempshow++;
									}
									if(tempshow==z2)
									{
										humanHas.put(selected, z2);
										int i=0;turn=2;
										while(i!=throwcounter)
									   {
											player1[add]=thrown[i];
											thrown[i].x=posx;
											thrown[i].y=posy;
											i++;add++;posx=posx+20;
								       }

									}
									else
									{
										int i=0;turn=1;
										while(i!=throwcounter)
										{
											player2[add2]=thrown[i];
											player2[add2].x=posx2;
											player2[add2].y=posy2;
											i++;add2++;posy2=posy2+20;
										}
				                   	}



				}
				selected=-1;throwcounter=0;
				for(int i=0;i<26;i++)
				thrown[i]=null;
				pass=0;
				c2=c3=c4=c5=0;
				//z1=z2=z3=z4=0;
			}
				btthrow.setEnabled(false);
				btshow.setEnabled(false);
				btpass.setEnabled(false);

						new Thread(
			  new Runnable() {
				public void run() {
					method1();
								  }
			 	          	 }
				).start();
     }


//CHECK


   public void check()
   {
	   if(pass>=4)
	   {
		   for(int i=0;i<26;i++)
		   thrown[i]=null;
		   throwcounter=0;c2=0;c3=0;c4=0;c5=0;pass=0;
		   z1=z2=z3=z4=0;
		   selected=-1;
		   passcounter++;
		   if(passcounter==4)
		   passcounter=0;
		   if(passcounter==0)
		   turn=1;
		   if(passcounter==1)
		   turn=2;
		   if(passcounter==2)
		   turn=3;
		   if(passcounter==3)
		   turn=4;

	   }
   }



//COMPUTER METHODS


	public void method1()
	{

		//PLAYER 2
		for(int i=0;i<z2;i++)
	    player2[index2[i]]=null;
	    for(int i=0;i<4;i++)
	    index2[i]=-1;
		z2=0;

		for(int i=0;i<52;i++)
		{
			if (player2[i] != null)
			{
				if(selected==(player2[i].des))
				{
					c3++;
				}
		    }

		}


		if(selected!=-1)
		{
		int ply1=0,ply2=0;
		if(last==1)
		{

		for(int i=0;i<52;i++)
		{
			if(player1[i]!=null)
			{
			if(selected==player1[i].des)
			ply1++;
		    }
		}
	}
	for(int i=0;i<52;i++)
	{
		if(player2[i]!=null)
		{
			ply2++;
		}
	}
	//AI purposes
	int probability = rd.nextInt(4);
	if(humanLied>1)
	{
		probability=0;
	}
	if(!humanHas.isEmpty())
	{
	Set s=humanHas.entrySet();

    //Move next key and value of Map by iterator
    Iterator it=s.iterator();

    while(it.hasNext())
    {
        // key=value separator this by Map.Entry to get key and value
        Map.Entry m =(Map.Entry)it.next();

        // getKey is used to get key of Map
        int key=(Integer)m.getKey();

        // getValue is used to get value of key in Map
        int value=(Integer)m.getValue();
        if(selected==key)
        {
        	if(value==4)
        		probability=1;
        	else
        		probability = rd.nextInt(8);
        	humanHas.remove(key);
        	break;
        }

    }
	}
	//AI purposes;
		if(((c3+c2>4 || probability==0 /*|| c2!=1*/) && (last!=2 && ply1!=c2)) && ply2>3)
		{

			showcounter2=1;
			truthcounter=0;



			if(last==1)
			{
				for(int i=0;i<z1;i++)
				{
					if(player1[index1[i]].des==selected)
					truthcounter++;
				}
				if(truthcounter==z1)
				{
					humanLied=0;
				int u=0;turn=1;
				while(u!=throwcounter)
				{
					player2[add2]=thrown[u];
					player2[add2].x=posx2;
					player2[add2].y=posy2;
					u++;add2++;posy2=posy2+20;
				}
			    }
			    else
			    {
			    	humanLied++;
					int u=0;turn=2;
					while(u!=throwcounter)
					{
						player1[add]=thrown[u];
						player1[add].x=posx;
						player1[add].y=posy;
						u++;add++;posx=posx+20;
					}
				}
			}


			if(last==4)
						{
							for(int i=0;i<z4;i++)
							{
								if(player4[index4[i]].des==selected)
								truthcounter++;
							}
							if(truthcounter==z4)
							{
							int u=0;turn=4;
							while(u!=throwcounter)
							{
								player2[add2]=thrown[u];
								player2[add2].x=posx2;
								player2[add2].y=posy2;
								u++;add2++;posy2=posy2+20;
							}
						    }
						    else
						    {
								int u=0;turn=2;
								while(u!=throwcounter)
								{
									player4[add4]=thrown[u];
									player4[add4].x=posx4;
									player4[add4].y=posy4;
									u++;add4++;posy4=posy4+20;
								}
							}
			            }




			            if(last==3)
									{
										for(int i=0;i<z3;i++)
										{
											if(player3[index3[i]].des==selected)
											truthcounter++;
										}
										if(truthcounter==z3)
										{
										int u=0;turn=3;
										while(u!=throwcounter)
										{
											player2[add2]=thrown[u];
											player2[add2].x=posx2;
											player2[add2].y=posy2;
											u++;add2++;posy2=posy2+20;
										}
									    }
									    else
									    {
											int u=0;turn=2;
											while(u!=throwcounter)
											{
												player3[add3]=thrown[u];
												player3[add3].x=posx3;
												player3[add3].y=posy3;
												u++;add3++;posx3=posx3+20;
											}
										}
			                        }




			selected=-1;throwcounter=0;c2=c3=c4=c5=0;
			for(int i=0;i<52;i++)
			thrown[i]=null;pass=0;
			//z1=z2=z3=z4=0;

		}





			else
			{
				pass++;

			    if(cfirst!=0)
			    {
			          for(int i=0;i<52;i++)
					{
						if(player2[i]!=null)
						{
						if(selected==(player2[i].des))
						{
							player2[i].x+=100;
							thrown[throwcounter]=player2[i];
							throwcounter++;
							last=2;
							pass=0;
							index2[z2]=i;
							z2++;


						}
					    }
		           }
			   }
		   }
}
                   //ai of computer in throwing

		           if(selected==-1 && turn==2)
		           {
					   int tempdes,descounter=0,lastdes=0,mostdes=-1;
					   for(int j=0;j<26;j++)
					   {
						   if(player2[j]!=null)
						   {
					         tempdes=player2[j].des;descounter=0;
					         for(int i=0;i<52;i++)
					         {

						     if(player2[i]!=null)
						      {
							   if(tempdes==player2[i].des)
							        {
										descounter++;
										if(lastdes<descounter)
										{
											mostdes=player2[i].des;
											lastdes=descounter;
										}
									}
						      }
					         }

				           }
				       }
				       if(rd.nextInt(2)==1)
				       {
				           for(int i=0;i<52;i++)
				           {
						   if(player2[i]!=null)
						   {
							   if(player2[i].des==mostdes)
							   {
						    index2[z2]=i;z2++;
						    player2[i].x+=100;
						    selected=mostdes;
						    thrown[throwcounter]=player2[i];
							throwcounter++;
							last=2;
							pass=0;
							c3++;
							   }
						   }
					      }
					    }
					    else
					    {
							for(int i=0;i<52;i++)
							{
								if(player2[i]!=null && player2[i].des==mostdes)
								{
									c3++;
								}
							}
							int fcounter=0;
							for(int i=0;i<52;i++)
							{
								if(player2[i]!=null && fcounter<c3 && player2[i].des!=mostdes)
								{
									fcounter++;
									index2[z2]=i;z2++;
									player2[i].x+=100;
								    selected=mostdes;
									thrown[throwcounter]=player2[i];
									throwcounter++;
							        last=2;
							        pass=0;

								}
							}
						}
				   }
				   if(pass!=0)
				   passpaint2=1;

		 //}
			   if(c3!=0)
			  { c2=c3;c3=0;}

repaint();
					try{Thread.sleep(1000);}catch(Exception e){}

			new Thread(
  new Runnable() {
	public void run() {
		method2();
	}
  }
).start();
}


//PLAYER 3
//if(showcounter2!=1)
//{
	public void method2()
	{
	for(int i=0;i<z3;i++)
	player3[index3[i]]=null;
	for(int i=0;i<4;i++)
	index3[i]=-1;
	z3=0;

    c4=0;
		for(int i=0;i<52;i++)
				{
					if(player3[i]!=null)
					{
					if(selected==(player3[i].des))
					{

						c4++;
					}
				    }
				}
	if(selected!=-1)
	{
		int ply3=0;
		for(int i=0;i<52;i++)
			{
				if(player3[i]!=null)
				{
					ply3++;
				}
	         }
				if((c2+c4>4 || rd.nextInt(5)==0) && last!=3 && c2!=1 && ply3>3)
				{
    			showcounter3=1;
    			truthcounter=0;
    			//show
    			if(last==1)
							{
								for(int i=0;i<z1;i++)
								{
									if(player1[index1[i]].des==selected)
									truthcounter++;
								}
								if(truthcounter==z1)
								{
								int u=0;turn=1;
								while(u!=throwcounter)
								{
									player3[add3]=thrown[u];
									player3[add3].x=posx3;
									player3[add3].y=posy3;
									u++;add3++;posx3=posx3+20;
								}
							    }
							    else
							    {
									int u=0;turn=3;
									while(u!=throwcounter)
									{
										player1[add]=thrown[u];
										player1[add].x=posx;
										player1[add].y=posy;
										u++;add++;posx=posx+20;
									}
								}
							}


							if(last==4)
										{
											for(int i=0;i<z4;i++)
											{
												if(player4[index4[i]].des==selected)
												truthcounter++;
											}
											if(truthcounter==z4)
											{
											int u=0;turn=4;
											while(u!=throwcounter)
											{
												player3[add3]=thrown[u];
												player3[add3].x=posx3;
												player3[add3].y=posy3;
												u++;add3++;posx3=posx3+20;
											}
										    }
										    else
										    {
												int u=0;turn=3;
												while(u!=throwcounter)
												{
													player4[add4]=thrown[u];
													player4[add4].x=posx4;
													player4[add4].y=posy4;
													u++;add4++;posy4=posy4+20;
												}
											}
							            }




							            if(last==2)
													{
														for(int i=0;i<z2;i++)
														{
															if(player2[index2[i]].des==selected)
															truthcounter++;
														}
														if(truthcounter==z2)
														{
														int u=0;turn=2;
														while(u!=throwcounter)
														{
															player3[add3]=thrown[u];
															player3[add3].x=posx3;
															player3[add3].y=posy3;
															u++;add3++;posx3=posx3+20;
														}
													    }
													    else
													    {
															int u=0;turn=3;
															while(u!=throwcounter)
															{
																player2[add2]=thrown[u];
																player2[add2].x=posx2;
																player2[add2].y=posy2;
																u++;add2++;posy2=posy2+20;
															}
														}
							                        }

				selected=-1;throwcounter=0;c2=c3=c4=c5=0;
		    	for(int i=0;i<52;i++)
		    	thrown[i]=null;pass=0;


		         }
					else
					{
						pass++;


                       if(cfirst!=0)
                       {
						for(int i=0;i<52;i++)
						{
							if(player3[i]!=null)
							{
								if(selected==(player3[i].des))
								{
									player3[i].y+=100;
									thrown[throwcounter]=player3[i];
						            throwcounter++;
						            last=3;
                                    pass=0;
                                    index3[z3]=i;
                                    z3++;
							    	}
						     }
		                  }
					   }
					  }//else ends
	}
										 //ai of player3 in throwing
										 if(selected==-1 && turn==3)
										 		           {
										 					   int tempdes,descounter=0,lastdes=0,mostdes=-1;
										 					   for(int j=0;j<52;j++)
										 					   {
										 						   if(player3[j]!=null)
										 						   {
										 					         tempdes=player3[j].des;descounter=0;
										 					         for(int i=0;i<52;i++)
										 					         {

										 						     if(player3[i]!=null)
										 						      {
										 							   if(tempdes==player3[i].des)
										 							        {
										 										descounter++;
										 										if(lastdes<descounter)
										 										{
										 											mostdes=player3[i].des;
										 											lastdes=descounter;
										 										}
										 									}
										 						      }
										 					         }

										 				           }
										 				       }
										 				       if(rd.nextInt(2)==1)
										 				       {
										 				       for(int i=0;i<52;i++)
										 				       {
										 						   if(player3[i]!=null)
										 						   {
										 							   if(player3[i].des==mostdes)
										 							   {
										 						    index3[z3]=i;z3++;
										 						    player3[i].y+=100;
										 						    selected=mostdes;
										 						    thrown[throwcounter]=player3[i];
										 							throwcounter++;
										 							last=3;
										 							pass=0;
										 							c4++;
										 							   }
										 						   }
															   }
										 					   }
					 else
						 {
							for(int i=0;i<52;i++)
							{
								if(player3[i]!=null && player3[i].des==mostdes)
								{
									c4++;
								}
							}
							int fcounter=0;
							for(int i=0;i<52;i++)
							{
								if(player3[i]!=null && fcounter<c4 && player3[i].des!=mostdes)
								{
									fcounter++;
									index3[z3]=i;z3++;
									player3[i].y+=100;
								    selected=mostdes;
									thrown[throwcounter]=player3[i];
									throwcounter++;
							        last=3;
							        pass=0;

								}
							}
						}
						}

		                            if(pass!=0)
								    passpaint3=1;


					if(c4!=0)
					{c2=c4;c4=0;}

					repaint();
					try{Thread.sleep(1000);}catch(Exception e){}

			new Thread(
 			 new Runnable() {
			public void run() {
			method3();
					}
					  }
				).start();
	}
//try{Thread.sleep(2000);}catch(Exception e){}

//PLAYER 4
	 public void method3()
	 {
	 for(int i=0;i<z4;i++)
							   player4[index4[i]]=null;
							   for(int i=0;i<4;i++)
			                   index4[i]=-1;
			               z4=0;

	    c5=0;
		for(int i=0;i<52;i++)
				{
					if(player4[i]!=null)
					{
					if(selected==(player4[i].des))
					{

						c5++;
					}
				}
				}
	if(selected!=-1)
	{
		int ply4=0;
				for(int i=0;i<52;i++)
					{
						if(player4[i]!=null)
						{
							ply4++;
				}}
				if((c2+c5>4 || rd.nextInt(5)==0) && last!=4 && c2!=1 && ply4>3)
				{
				showcounter4=1;
				truthcounter=0;
				if(last==1)
							{
								for(int i=0;i<z1;i++)
								{
									if(player1[index1[i]].des==selected)
									truthcounter++;
								}
								if(truthcounter==z1)
								{
								int u=0;turn=1;
								while(u!=throwcounter)
								{
									player4[add4]=thrown[u];
									player4[add4].x=posx4;
									player4[add4].y=posy4;
									u++;add4++;posy4=posy4+20;
								}
							    }
							    else
							    {
									int u=0;turn=4;
									while(u!=throwcounter)
									{
										player1[add]=thrown[u];
										player1[add].x=posx;
										player1[add].y=posy;
										u++;add++;posx=posx+20;
									}
								}
							}


							if(last==2)
										{
											for(int i=0;i<z2;i++)
											{
												if(player2[index2[i]].des==selected)
												truthcounter++;
											}
											if(truthcounter==z2)
											{
											int u=0;turn=2;
											while(u!=throwcounter)
											{
												player4[add4]=thrown[u];
												player4[add4].x=posx4;
												player4[add4].y=posy4;
												u++;add4++;posy4=posy4+20;
											}
										    }
										    else
										    {
												int u=0;turn=4;
												while(u!=throwcounter)
												{
													player2[add2]=thrown[u];
													player2[add2].x=posx2;
													player2[add2].y=posy2;
													u++;add2++;posy2=posy2+20;
												}
											}
							            }




							            if(last==3)
													{
														for(int i=0;i<z3;i++)
														{
															if(player3[index3[i]].des==selected)
															truthcounter++;
														}
														if(truthcounter==z3)
														{
														int u=0;turn=3;
														while(u!=throwcounter)
														{
															player4[add4]=thrown[u];
															player4[add4].x=posx4;
															player4[add4].y=posy4;
															u++;add4++;posy4=posy4+20;
														}
													    }
													    else
													    {
															int u=0;turn=4;
															while(u!=throwcounter)
															{
																player3[add3]=thrown[u];
																player3[add3].x=posx3;
																player3[add3].y=posy3;
																u++;add3++;posx3=posx3+20;
															}
														}
							                        }

				selected=-1;throwcounter=0;c2=c3=c4=c5=0;
				for(int i=0;i<52;i++)
		    	thrown[i]=null;pass=0;

		         }
				else
				{
					pass++;

					if(cfirst!=0)
					{
					for(int i=0;i<52;i++)
					{
						if(player4[i]!=null)
						{
							//lie
							if(humanShowTimes<-1 && selected!=(player4[i].des))
							   {
								player4[i].x-=100;
								thrown[throwcounter]=player4[i];
							    throwcounter++;
							    last=4;
                                pass=0;
                                index4[z4]=i;
                                z4++;
                                continue;
                                
							   }
							//truth
						if(selected==(player4[i].des))
							{
								player4[i].x-=100;
								thrown[throwcounter]=player4[i];
							    throwcounter++;
							    last=4;
                                pass=0;
                                index4[z4]=i;
                                z4++;
							}
						}
		            }
				}
			}//else ends
}
				//ai of player 4


				if(selected==-1 && turn==4)
						           {
									   int tempdes,descounter=0,lastdes=0,mostdes=-1;
									   for(int j=0;j<52;j++)
									   {
										   if(player4[j]!=null)
										   {
									         tempdes=player4[j].des;descounter=0;
									         for(int i=0;i<26;i++)
									         {

										     if(player4[i]!=null)
										      {
											   if(tempdes==player4[i].des)
											        {
														descounter++;
														if(lastdes<descounter)
														{
															mostdes=player4[i].des;
															lastdes=descounter;
														}
													}
										      }
									         }
								           }
								       }
									   int probability = rd.nextInt(2);
									   if(humanShowTimes<-1)
									   {
										   probability = 1;//lie
									   }
								       if(probability==0)
								       {
								       for(int i=0;i<52;i++)
								       {
										   if(player4[i]!=null)
										   {
											   if(player4[i].des==mostdes)
											   {
										    index4[z4]=i;z4++;
										    player4[i].x-=100;
										    selected=mostdes;
										    thrown[throwcounter]=player4[i];
											throwcounter++;
											last=4;
											pass=0;
											c5++;
											   }
										   }
									   }
								   }
								   else
								   {
									   for(int i=0;i<26;i++)
									   							{
									   								if(player4[i]!=null && player4[i].des==mostdes)
									   								{
									   									c5++;
									   								}
									   							}
									   if(humanShowTimes<0)
									   {
										   c5 = humanShowTimes<-4?4:0-humanShowTimes;//lie
									   }
									   							int fcounter=0;
									   							for(int i=0;i<52;i++)
									   							{
									   								if(player4[i]!=null && (fcounter<c5) && player4[i].des!=mostdes)
									   								{
									   									fcounter++;
									   									index4[z4]=i;z4++;
									   									player4[i].x-=100;
									   								    selected=mostdes;
									   									thrown[throwcounter]=player4[i];
									   									throwcounter++;
									   							        last=4;
									   							        pass=0;

									   								}
									   							}

								   }
				   }
		            if(pass!=0)
		            passpaint4=1;

				if(c5!=0)
				{c2=c5;c5=0;}








                            for(int i=0;i<z1;i++)
						 	player1[index1[i]]=null;
						    for(int i=0;i<4;i++)
						    index1[i]=-1;
						 	z1=0;
		btthrow.setEnabled(true);
		btshow.setEnabled(true);
		btpass.setEnabled(true);

        if(selected==-1 && turn==1)
        urturn=1;
		repaint();
		if((selected==-1) && (turn==2 || turn==3 || turn==4))
		comprecall();

	}
	public void comprecall()
	{
		new Thread(
										  new Runnable() {
											public void run() {

												method1();

											}
										  }
					).start();
	}
}