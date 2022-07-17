package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import data_structures.LinkedList;

public class LinkedListUI extends JFrame
{
	private static final long serialVersionUID = 6857274362816000028L;
	
	public static final String APPLICATION_NAME = "LinkedListUI",
							   VERSION = "1.0.2";
	
	private static final int INVALID_INDEX = -199;
	
	private static enum Type{Integer, String};
	
	private static enum IteratorType{Iterator, ListIterator};
	
	private Type type;
	private JPanel mainPane;
	private JTextArea display;
	private JScrollPane jsp;	
	private JMenuBar menuBar;
	
	private JMenu fileMenu,
				  methodsMenu,
				  editMenu,
				  iteratorMenu;
				  
	private JMenuItem clearUiMenuItem,
					  newLLString,
					  newLLInteger,
					  closeMenuItem,
					  sizeMenuItem,
					  clearMenuItem,
					  containsMenuItem,
					  isEmptyMenuItem,
					  addMenuItem,
					  removeMenuItem,
					  listIteratorMenuItem,
					  liPosMenuItem,
					  addFirtstMenuItem,
					  addLastMenuItem,
					  removeFirstMenuItem,
					  removeLastMenuItem,
					  getFirstmenuItem,
					  getLastMenuItem,
					  toStrinMenuItem,
					  hashCodeMenuItem,
					  sortMenuItem,
					  itHasNextMenuItem,
					  itNextMenuItem,
					  itRemoveMenuItem,
					  iteratorMenuItem,
					  itCloseMenuItem,
					  itShowStatusMenuItem,
					  itAddMenuItem,
					  itHasPreviousMenuItem,
					  itNextindexMenuItem,
					  itPreviousMenuItem,
					  itPreviousIndexMenuItem,
					  itSetMenuItem;
						  
	private final Dimension SCREEN_DIMENSION,
	      				    WINDOW_DIMENSION = new Dimension(650, 300);
	private LinkedList ll;
	private ListIterator listIterator;
	
	
	public LinkedListUI()
	{
		SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle(APPLICATION_NAME);
 
		setMinimumSize(WINDOW_DIMENSION);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		
		newLLInteger = new JMenuItem("New LinkedList<Integer>");
		newLLInteger.addActionListener(new ActionHandler());
		
		closeMenuItem = new JMenuItem("Close");
		closeMenuItem.addActionListener(new ActionHandler());
		fileMenu.add(newLLInteger);
		
		newLLString = new JMenuItem("New LinkedList<String>");
		newLLString.addActionListener(new ActionHandler());
		
		fileMenu.add(newLLString);
		fileMenu.addSeparator();
		fileMenu.add(closeMenuItem);
		
		editMenu = new JMenu("Edit");
		clearUiMenuItem = new JMenuItem("Clear");
		clearUiMenuItem.addActionListener(new ActionHandler());
		editMenu.add(clearUiMenuItem);
		
		methodsMenu = new JMenu("Methods");
		
		sizeMenuItem = new JMenuItem("size()");
		sizeMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(sizeMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(methodsMenu);
		
		clearMenuItem = new JMenuItem("clear()");
		clearMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(clearMenuItem);
		
		containsMenuItem = new JMenuItem("contains(e)");
		containsMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(containsMenuItem);
		
		isEmptyMenuItem = new JMenuItem("isEmpy()");
		isEmptyMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(isEmptyMenuItem);
		
		addMenuItem = new JMenuItem("add(e)");
		addMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(addMenuItem);
		
		removeMenuItem = new JMenuItem("remove(e)");
		removeMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(removeMenuItem);
		
		iteratorMenuItem = new JMenuItem("iterator()");
		iteratorMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(iteratorMenuItem);
		
		listIteratorMenuItem = new JMenuItem("listIterator()");
		listIteratorMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(listIteratorMenuItem);
		
		liPosMenuItem = new JMenuItem("listIterator(pos)");
		liPosMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(liPosMenuItem);
		
		addFirtstMenuItem = new JMenuItem("addFirst(e)");
		addFirtstMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(addFirtstMenuItem);
		
		addLastMenuItem = new JMenuItem("addLast(e)");
		addLastMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(addLastMenuItem);
		
		removeFirstMenuItem = new JMenuItem("removeFirst()");
		removeFirstMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(removeFirstMenuItem);
		
		removeLastMenuItem = new JMenuItem("removeLast()");
		removeLastMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(removeLastMenuItem);
		
		getFirstmenuItem = new JMenuItem("getFirst()");
		getFirstmenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(getFirstmenuItem);
		
		getLastMenuItem = new JMenuItem("getLast()");
		getLastMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(getLastMenuItem);
		
		sortMenuItem = new JMenuItem("sort()");
		sortMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(sortMenuItem);
		
		toStrinMenuItem = new JMenuItem("toString()");
		toStrinMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(toStrinMenuItem);
		
		hashCodeMenuItem = new JMenuItem("hashCode()");
		hashCodeMenuItem.addActionListener(new ActionHandler());
		methodsMenu.add(hashCodeMenuItem);
		
		iteratorMenu = new JMenu("Iterator");
		menuBar.add(iteratorMenu);
		
		itHasNextMenuItem = new JMenuItem("hasNext()");
		itHasNextMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itHasNextMenuItem);
		
		itNextMenuItem = new JMenuItem("next()");
		itNextMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itNextMenuItem);
		
		itRemoveMenuItem = new JMenuItem("remove()");
		itRemoveMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itRemoveMenuItem);
		
		iteratorMenu.addSeparator();
		
		itNextindexMenuItem = new JMenuItem("nextIndex()");
		itNextindexMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itNextindexMenuItem);
		
		itHasPreviousMenuItem = new JMenuItem("hasPrevious()");
		itHasPreviousMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itHasPreviousMenuItem);
		
		itPreviousMenuItem = new JMenuItem("previous()");
		itPreviousMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itPreviousMenuItem);
		
		itPreviousIndexMenuItem = new JMenuItem("previousIndex()");
		itPreviousIndexMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itPreviousIndexMenuItem);
		
		itAddMenuItem = new JMenuItem("add(e)");
		itAddMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itAddMenuItem);
		
		itSetMenuItem = new JMenuItem("set(e)");
		itSetMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itSetMenuItem);
		
		iteratorMenu.addSeparator();
		itCloseMenuItem = new JMenuItem("Close");
		itCloseMenuItem.addActionListener(new ActionHandler());
		itShowStatusMenuItem = new JMenuItem("Show status");
		itShowStatusMenuItem.addActionListener(new ActionHandler());
		iteratorMenu.add(itShowStatusMenuItem);
		iteratorMenu.add(itCloseMenuItem);
		
		mainPane = new JPanel();
		getContentPane().add(mainPane, BorderLayout.SOUTH);
		
		display = new JTextArea(30,75);
		display.setFont(new Font("Monospaced", Font.BOLD, 12));
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		display.setEditable(false);
		
		jsp = new JScrollPane(display);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		getContentPane().add(jsp, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMethodsMenuStatus(false);
		setupListIteratorMenu(false, null);
		setLookAndFeel();
		setBounds(SCREEN_DIMENSION.width / 2 - WINDOW_DIMENSION.width / 2,
				SCREEN_DIMENSION.height / 2 - WINDOW_DIMENSION.height / 2, 
				WINDOW_DIMENSION.width, WINDOW_DIMENSION.height);
	}
	
	
	private void setupListIteratorMenu(boolean status, IteratorType it)
	{
		if(status)
		{
			listIterator = ll.listIterator();
			setMethodsMenuStatus(false);
			
			if(it.equals(IteratorType.ListIterator))
			{
				iteratorMenu.setText("ListIterator");
				setListIteratorMethodsStatus(true);
			}
			else
			{
				iteratorMenu.setText("Iterator");
				setDefaultIteratorMethodsStatus(true);
				setListIteratorMethodsStatus(false);
			}
		}
		iteratorMenu.setVisible(status);
	}
	
	private void setupListIteratorMenu(int index)
	{
		listIterator = ll.listIterator(index);
		setMethodsMenuStatus(false);
		iteratorMenu.setText("ListIterator");
		setListIteratorMethodsStatus(true);
		iteratorMenu.setVisible(true);	
	}
	
	
	private void onIterarorSelected()
	{
		setupListIteratorMenu(true, IteratorType.Iterator);
	}
	
	
	private void onListIteratorSelected()
	{
		setupListIteratorMenu(true, IteratorType.ListIterator);
	}
	
	
	private void setListIteratorMethodsStatus(boolean status)
	{
		itAddMenuItem.setEnabled(status);
		itHasPreviousMenuItem.setEnabled(status);
		itNextindexMenuItem.setEnabled(status);
		itPreviousMenuItem.setEnabled(status);
		itPreviousIndexMenuItem.setEnabled(status);
		itSetMenuItem.setEnabled(status);
	}
	
	
	private void setDefaultIteratorMethodsStatus(boolean status)
	{
		itHasNextMenuItem.setEnabled(status);;
		itNextMenuItem.setEnabled(status);
		itRemoveMenuItem.setEnabled(status);
	}
	
	
	private void setMethodsMenuStatus(boolean status)
	{
		methodsMenu.setEnabled(status);
	}
	
	
	private void setLookAndFeel()
	{	
		try
		{	
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	private void updateDisplay(String str)
	{
		display.append(str+"\n\n");
	}
	
	
	 private Object askElement()
	 {
		 if(type.equals(Type.String))
		 {
			 String str = getString();
			 if(str == null || str.length() == 0)
			 {
				 if(getInvalidInputDialogOption() == JOptionPane.YES_OPTION)
				 {
					 return askElement();
				 }
				 return null;
			 }
			 return str;
		 }
		 else
		 {
			 String str = getString();
			 if(str == null || str.length() == 0 || !str.matches("\\-?\\d+"))
			 {
				 if(getInvalidInputDialogOption() == JOptionPane.YES_OPTION)
				 {
					 return askElement();
				 }
				 return null;
			 }
			 return Integer.parseInt(str);
		 }
	 }
	 
	
	private String getString()
	{
		if(type.equals(Type.Integer))
		{
			return JOptionPane.showInputDialog(this, "Insert an integer");
		}
		return JOptionPane.showInputDialog(this, "Insert a string");
	}
	

	private int getListIteratorIndex()
	{
		 String str = JOptionPane.showInputDialog(this, "Insert an index for the ListIterator");
		 if(str == null || str.length() == 0 || !str.matches("\\-?\\d+"))
		 {
			 if(getInvalidIndexInputDialogOption() == JOptionPane.YES_OPTION)
			 {
				 return getListIteratorIndex();
			 }
			 return INVALID_INDEX;
		 }
		 int index = Integer.parseInt(str);
		 if(index < 0 || index > ll.size())
		 {
			 if(getInvalidIndexInputDialogOption() == JOptionPane.YES_OPTION)
			 {
				 return getListIteratorIndex();
			 }
			 return INVALID_INDEX;
		 }
		 return index;
	}
	
	
   	private int getInvalidInputDialogOption()
	{
		Toolkit.getDefaultToolkit().beep();
		int option = JOptionPane.showConfirmDialog(this, "Try again?","Invalid input", JOptionPane.YES_NO_OPTION);
		return option;
	}
   	
   	
   	private int getInvalidIndexInputDialogOption()
	{
		Toolkit.getDefaultToolkit().beep();
		int option = JOptionPane.showConfirmDialog(this, "Index could in range 0.."+ll.size()+" included."+"\nTry again?","Invalid index", 
				JOptionPane.YES_NO_OPTION);
		return option;
	}
   	
	
	private void setNewMenuItemsStatus(boolean status)
	{
		newLLString.setEnabled(status);
		newLLInteger.setEnabled(status);
	}
	
	
	private void onListTypeSelected()
	{
		setNewMenuItemsStatus(false);
		setMethodsMenuStatus(true);
	}
	
	
	
	private void llSize()
	{
		updateDisplay("Size = "+ll.size());
	}
	
	
	private void llClear()
	{
		ll.clear();
		updateDisplay("List cleared.");
	}
	
	
	@SuppressWarnings("unchecked")
	private void llContains()
	{
		Object o = askElement();
		if(ll.contains(o))
		{
			 updateDisplay(o+" is contained in the list.");
		}
		else
		{
			 updateDisplay(o+" isn't contained in the list.");
		}
	}
	 
	 
	 private void llIsEmpty()
	 {
		 updateDisplay("isEmpty = "+ll.isEmpty());
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 private void llAdd()
	 {
		 Object o = askElement();
		 if(o != null)
		 {
			 ll.add(o);
			 updateDisplay(o+" added to the list.");
		 }
	 }
	 
	 @SuppressWarnings("unchecked")
	 private void llRemove()
	 {
		 Object o = askElement();
		 if(o != null)
		 {
			 ll.remove(o);
			 updateDisplay(o+" remove attempted.");
		 }
	 }
	 
	 @SuppressWarnings("unchecked")
	 private void llAddFirst()
	 {
		 Object o = askElement();
		 if(o != null)
		 {
			 ll.addFirst(o);
			 updateDisplay(o+" added at the head of the list.");
		 }
	 }
	 
	 @SuppressWarnings("unchecked")
	 private void llAddLast()
	 {
		 Object o = askElement();
		 if(o != null)
		 {
			 ll.addFirst(o);
			 updateDisplay(o+" added at the end of the list.");
		 }
	 }
	 
	 
	 private void llRemoveFirst()
	 {
		 Object o = ll.removeFirst();
		 updateDisplay(o+" removed from the head of the list.");
	 }
	 
	 
	 private void llRemoveLast()
	 {
		 Object o = ll.removeLast();
		 updateDisplay(o+" removed from the end of the list.");
	 }
	 
	 
	 private void llGetFirst()
	 {
		 updateDisplay(ll.getFirst()+" got from the head of the list.");
	 }
	 
	 
	 private void llGetLast()
	 {
		 updateDisplay(ll.getLast()+" got from the end of the list.");
	 }
	
	 @SuppressWarnings("unchecked")
	 private void llSort()
	 {
		if(type.equals(Type.Integer))
		{
			ll.sort(new Comparator<Integer>(){

				@Override
				public int compare(Integer o1, Integer o2)
				{
					return o1.compareTo(o2);
				}
			});
		}
		else
		{
			ll.sort(new Comparator<String>(){

				@Override
				public int compare(String o1, String o2)
				{
					return o1.compareTo(o2);
				}
			});
		}
		updateDisplay("The list has just been sorted.");
	 }
	 
	 
	 private void llToString()
	 {
		 updateDisplay(ll.toString());
	 }
	 
	 
	 private void llHashCode()
	 {
		 updateDisplay(ll.hashCode()+"");
	 }
	 
	 
	 private void itHasNext()
	 {
		 updateDisplay("hasNext() = "+listIterator.hasNext());
	 }
	 
	 
	 private void itNext()
	 {
		 try
		 {
			 Object o = listIterator.next();
			 updateDisplay(o.toString());
		 }
		 catch(Exception e)
		 {
			 updateDisplay(e.toString());
		 }
		 showItStatus();
	 }
	 
	 
	 private void itRemove()
	 {
		 try
		 {
			 listIterator.remove();
			 updateDisplay("Remove performed.");
		 }
		 catch(Exception e)
		 {
			 updateDisplay(e.getMessage());
		 }
	 }
	 
	 private static String extensString(String str)
	 {
		return str.replace(",", "  ,  ");
	 }
	 
	 private void showItStatus()
	 {
		 int previousIndex = listIterator.previousIndex();
		 String str = extensString(ll.toString());
		 str = "[    " + str.substring(1,str.length()-1) + "    ]";
		 updateDisplay(str);
		 System.out.println(str);
		 if(previousIndex == -1)
		 {
			 display.append("   ^\n");
			 System.out.println("    ^\n");
		 }
		 else if(previousIndex == ll.size()-1)
		 {
			 display.append(str.substring(0, str.length()-2).replaceAll(".", " ")+"^\n");
			 System.out.println(str.substring(0,str.length()-2).replaceAll(".", " ")+"^\n");
		 }
		 else
		 {
			 LinkedList tmp = new LinkedList<>();
			 ListIterator lit = ll.listIterator();
			 while(lit.previousIndex() < previousIndex)
			 {
				tmp.add(lit.next());
			 }
			 String s = extensString(tmp.toString());
			 s = "[    " + s.substring(1,s.length()-1)+" ";
			 display.append(s.replaceAll(".", " ")+"^\n");
			 System.out.println(s.replaceAll(".", " ")+"^\n");
		 }
	 }
	 
	 
	 private void itClose()
	 {
		 listIterator = null;
		 setupListIteratorMenu(false, null);
		 setMethodsMenuStatus(true);
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 private void itAdd()
	 {
		 Object o = askElement();
		 if(o != null)
		 {
			 try
			 {
				 listIterator.add(o);
				 updateDisplay("add("+o+") performed.");
			 }
			 catch (Exception e) 
			 {
				 updateDisplay(e.toString());
			 }
		 };
	 }
	 
	 
	 private void itHashPrevious()
	 {
		 updateDisplay("hasPrevious() = "+listIterator.hasPrevious());
	 }
	 
	 
	 private void itPrevious()
	 {
		 try
		 {
			 Object o = listIterator.previous();
			 updateDisplay(o.toString());
		 }
		 catch(Exception e)
		 {
			 updateDisplay(e.toString());
		 }
		 showItStatus();
	 }
	 
	 
	 private void itNextIndex()
	 {
		 updateDisplay("nextIndex() = "+listIterator.nextIndex());
	 }
	 
	 
	 private void itPreviousIndex()
	 {
		 updateDisplay("previousIndex() = "+listIterator.previousIndex()); 
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 private void itSet()
	 {
		 Object o = askElement();
		 if(o != null)
		 {
			 try
			 {
				 listIterator.set(o);
				 updateDisplay("set("+o+") performed.");
			 }
			 catch (Exception e) 
			 {
				 updateDisplay(e.toString());
			 }
		 };
	 }
	 
	 /*
	 //////////////////////////////////////////////////////////////////////////////////////
	*/
	
	 @SuppressWarnings("unchecked")
	private class ActionHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			
			if(e.getSource() == closeMenuItem)
			{
				System.exit(0);
			}
			
			if(e.getSource() == newLLInteger)
			{
				ll = new data_structures.LinkedList<Integer>();
				type = Type.Integer;
				setTitle(getTitle()+"<Integer>");
				onListTypeSelected();
				return;
			}
			
			if(e.getSource() == newLLString)
			{
				ll = new data_structures.LinkedList<String>();
				type = Type.String;
				setTitle(getTitle()+"<String>");
				onListTypeSelected();
				return;
			}
			
			if(e.getSource() == sizeMenuItem)
			{
				llSize();
				return;
			}
			
			if(e.getSource() == clearMenuItem)
			{
				llClear();
				return;
			}
			
			if(e.getSource() == containsMenuItem)
			{
				llContains();
				return;
			}
			
			if(e.getSource() == isEmptyMenuItem)
			{
				llIsEmpty();
				return;
			}
			
			if(e.getSource() == addMenuItem)
			{
				llAdd();
				return;
			}
			
			if(e.getSource() == removeMenuItem)
			{
				llRemove();
				return;
			}
			
			if(e.getSource() == addFirtstMenuItem)
			{
				llAddFirst();
				return;
			}
			
			if(e.getSource() == addLastMenuItem)
			{
				llAddLast();
				return;
			}
			
			if(e.getSource() == removeFirstMenuItem)
			{
				llRemoveFirst();
				return;
			}
			
			if(e.getSource() == removeLastMenuItem)
			{
				llRemoveLast();
				return;
			}
			
			if(e.getSource() == getFirstmenuItem)
			{
				llGetFirst();
				return;
			}
			if(e.getSource() == getLastMenuItem)
			{
				llGetLast();
				return;
			}
			
			if(e.getSource() == sortMenuItem)
			{
				llSort();
				return;
			}
			
			if(e.getSource() == toStrinMenuItem)
			{
				llToString();
				return;
			}
			
			if(e.getSource() == hashCodeMenuItem)
			{
				llHashCode();
				return;
			}
			
			if(e.getSource() == iteratorMenuItem)
			{
				onIterarorSelected();
				return;
			}
			
			if(e.getSource() == listIteratorMenuItem)
			{
				onListIteratorSelected();
				return;
			}
			
			if(e.getSource() == liPosMenuItem)
			{
				int index = getListIteratorIndex();
				if(index != INVALID_INDEX)
				{
					setupListIteratorMenu(index);
				}
				return;
			}
			
			if(e.getSource() == itHasNextMenuItem)
			{
				itHasNext();
				return;
			}
			
			if(e.getSource() == itNextMenuItem)
			{
				itNext();
				return;
			}
			
			if(e.getSource() == itRemoveMenuItem)
			{
				itRemove();
				return;
			}
			
			if(e.getSource() == itCloseMenuItem)
			{
				itClose();
				return;
			}
			
			if(e.getSource() == itAddMenuItem)
			{
				itAdd();
				return;
			}
			
			if(e.getSource() == itHasPreviousMenuItem)
			{
				itHashPrevious();
				return;
			}
			
			if(e.getSource() == itNextindexMenuItem)
			{
				itNextIndex();
				return;
			}
			
			if(e.getSource() == itPreviousMenuItem)
			{
				itPrevious();
				return;
			}
			
			if(e.getSource() == itPreviousIndexMenuItem)
			{
				itPreviousIndex();
				return;
			}
			
			if(e.getSource() == itSetMenuItem)
			{
				itSet();
				return;
			}
			
			
			if(e.getSource() == itShowStatusMenuItem)
			{
				showItStatus();
				return;
			}
			
			if(e.getSource() == clearUiMenuItem)
			{
				display.setText("");
				return;
			}
			
			//////////////////////////////////////////////////////////////////////
			
		}
		
	}
		
	 
	public static void main(String[] args)
	{
		new LinkedListUI().setVisible(true);
	}
}
