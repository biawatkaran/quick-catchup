# GWT

**Stock Application** 

To add or delete any stock symbol and generates randomly some prices for those markets 

***Run:***

* Run mvn clean install - will generate war file
* Now run gwt:run plugin


***Basics:***
 
 GWT application needs to have both client and server side. Client will have an interface whose implementation is on
 server side. Same interface needs to have Async for Callbacks.
 GWT projects have there corresponding modules defined in X.gwt.xml file. The module name is referred in web.xml to
 find the servlet along with relative path defined in Client Interface. in our case ***/stockwatcher/stockPrices***
 
 if we want to include any other gwt module, as in adding dependency, it's done in X.gwt.xml using <source path='client'/>
 This is used as to add the translatable code.
 
 Add widgets
 * Name-Of-Widget extend class Composite
 
 * create an interface by name UiBinder e.g. if CustomerWidget.ui.xml then interface would be CustomerWidgetUiBinder
   which extends UiBinder<Widget, Name-Of-Widget>
   
 * ui.xml file is something which adds page components.
 
 * in order to refer any component dynamically in class to populate etc, in xml file ui:field='fName'
 
 * same is linked in java file using @UiField MaterialTextBox fName; or @UiField MaterialButton btnDisp;
 
 * apply handler on them as: @UiHandler("btnDisp") void onDisplay(ClickEvent e){...


Used <inherits name="gwt.material.design.GwtMaterialDesign" /> in order to have some nice UI. Those can be referred in 
ui xml file

<ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
             xmlns:g="urn:import:com.google.gwt.user.client.ui"
             xmlns:m="urn:import:gwt.material.design.client.ui">
             
                 <m:MaterialRow>
                     <m:MaterialColumn grid="s12 m4 l4" offset="l1" >
                         <m:MaterialTitle title="Customer" description="Please provide your details."/>
                         <m:MaterialPanel padding="5" shadow="1" addStyleNames="{style.panel}">
                             <m:MaterialPanel addStyleNames="{style.fieldPanel}">
             
                                 <m:MaterialTextBox ui:field='fName' type="text" placeholder="First Name"/>
                                 <m:MaterialTextBox type="text" placeholder="Last Name" />
                                 <m:MaterialColumn grid="s6"> <m:MaterialButton ui:field="btnDisp" color="red" waves="light" text="Display" width="100%"/> </m:MaterialColumn>
                                 <m:MaterialColumn grid="s6"> <m:MaterialButton ui:field="btnClear" color="red" waves="light" text="Clear" width="100%"/> </m:MaterialColumn>
             
                             </m:MaterialPanel>
                         </m:MaterialPanel>
                     </m:MaterialColumn>
                 </m:MaterialRow>
 
  
