import java.awt.Color;
import java.awt.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//Main Menu Buttons
		if(mx >= 430 && mx <= 580) {
			if(my >= 200 && my <= 250) {
				if(GamePanel.isMenuOpen)
					GamePanel.State = GamePanel.STATE.GAME;
			}
		}
		//options
		if(mx >= 430 && mx <= 580) {
			if(my >= 300 && my <= 350) {
				if(GamePanel.isMenuOpen)
					GamePanel.State = GamePanel.STATE.OPTIONS_MENU;
				
			}
		}
		//arrow
		if(mx >= 25 && mx <= 105) {
			if(my >= 25 && my <= 75) {
				if(GamePanel.isMenu2Open || GamePanel.isMenu3Open) {
			          GamePanel.State = GamePanel.STATE.MAIN_MENU;
				}
					
			}
		}
		if(mx >= 430 && mx <= 580) {
			if(my >= 400 && my <= 450) {
				if(GamePanel.isMenuOpen)
					GamePanel.State = GamePanel.STATE.SHOP_MENU;

			}
		}
		//ShopMenu buttons
		if(mx >= 75 && mx <= 325) {
			if(my >= 200 && my <= 350) {
				if(GamePanel.isMenu2Open) {
					GamePanel.imgName = "choco.png";
					unchooseSkin();
					ShopMenu.choco_choosed = true;
				}

			}
		}
		if(mx >= 375 && mx <= 625) {
			if(my >= 200 && my <= 350) {
				if(GamePanel.isMenu2Open) {
					GamePanel.imgName = "mud.png";
					System.out.println(GamePanel.imgName);
					unchooseSkin();
					ShopMenu.mud_choosed = true;
				}

			}
		}
		if(mx >= 675 && mx <= 925) {
			if(my >= 200 && my <= 350) {
				if(GamePanel.isMenu2Open) {
					GamePanel.imgName = "lava.png";
					System.out.println(GamePanel.imgName);
					unchooseSkin();
					ShopMenu.lava_choosed = true;
				}
			}
		}
		if(mx >= 75 && mx <= 325) {
			if(my >= 450 && my <= 600) {
				if(GamePanel.isMenu2Open) {
					GamePanel.imgName = "madness.png";
					unchooseSkin();
					ShopMenu.madness_choosed = true;
				}
			}
		}
		if(mx >= 375 && mx <= 625) {
			if(my >= 450 && my <= 600) {
				if(GamePanel.isMenu2Open) {
					GamePanel.imgName = "inf.png";
					System.out.println(GamePanel.imgName);
					unchooseSkin();
					ShopMenu.inferno_choosed = true;
				}
			}
		}
		if(mx >= 675 && mx <= 925) {
			if(my >= 450 && my <= 600) {
				if(GamePanel.isMenu2Open) {
					GamePanel.imgName = "nano.png";
					unchooseSkin();
					ShopMenu.nano_choosed = true;
				}
			}
		}
		//OptionsMenu
		if(mx >= 75 && mx <= 325) {
			if(my >= 400 && my <= 550) {
				if(GamePanel.isMenu3Open) {
					unchooseMap();
					OptionsMenu.map1_choosed = true;
					GamePanel.map_num = 0;
					OptionsMenu.randomBtn_choosed = false;
					GamePanel.generate = false;

				}

			}
		}
		if(mx >= 375 && mx <= 625) {
			if(my >= 400 && my <= 550) {
				if(GamePanel.isMenu3Open) {
					unchooseMap();
					OptionsMenu.map2_choosed = true;
					GamePanel.map_num = 1;
					OptionsMenu.randomBtn_choosed = false;
					GamePanel.generate = false;
				}

			}
		}
		if(mx >= 675 && mx <= 925) {
			if(my >= 400 && my <= 550) {
				if(GamePanel.isMenu3Open) {
					unchooseMap();
					OptionsMenu.map3_choosed = true;
					GamePanel.map_num = 2;
					OptionsMenu.randomBtn_choosed = false;
					GamePanel.generate = false;
				}

			}
		}
		
		if(mx >= 250 && mx <= 390) {
			if(my >= 200 && my <= 240) {
				if(GamePanel.isMenu3Open) {
					unchooseBtns();
					OptionsMenu.btn1_choosed = true;
					GamePanel.DELAY = 300;
				}

			}
		}
		if(mx >= 435 && mx <= 595) {
			if(my >= 200 && my <= 240) {
				if(GamePanel.isMenu3Open) {
					unchooseBtns();
					OptionsMenu.btn2_choosed = true;
					GamePanel.DELAY = 170;
				}

			}
		}
		if(mx >= 620 && mx <= 780) {
			if(my >= 200 && my <= 240) {
				if(GamePanel.isMenu3Open) {
					unchooseBtns();
					OptionsMenu.btn3_choosed = true;
					GamePanel.DELAY = 120;
				}

			}
		}
		if(mx >= 320 && mx <= 680) {
			if(my >= 585 && my <= 625) {
				if(GamePanel.isMenu3Open) {
					unchooseMap();
					OptionsMenu.randomBtn_choosed = !OptionsMenu.randomBtn_choosed;
					GamePanel.generate = !GamePanel.generate;
				}

			}
		}
		//EndMenu
		if(mx >= 370 && mx <= 670) {
			if(my >= 380 && my <= 430) {
				if(GamePanel.isMenu4Open) {
					GamePanel.State = GamePanel.STATE.MAIN_MENU;
				}

			}
		}
		
	}
	
	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void unchooseSkin() {
		ShopMenu.choco_choosed = false;
		ShopMenu.mud_choosed = false;
		ShopMenu.lava_choosed = false;
		ShopMenu.madness_choosed = false;
		ShopMenu.inferno_choosed = false;
		ShopMenu.nano_choosed = false;
	}
	public static void unchooseMap() {
		OptionsMenu.map1_choosed = false;
		OptionsMenu.map2_choosed = false;
		OptionsMenu.map3_choosed = false;
	}
	
	public static void unchooseBtns() {
		OptionsMenu.btn1_choosed = false;
		OptionsMenu.btn2_choosed = false;
		OptionsMenu.btn3_choosed = false;
	}

}
