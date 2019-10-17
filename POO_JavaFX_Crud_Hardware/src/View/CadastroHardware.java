package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import Controller.ControllerHardware;
import Model.Hardware;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CadastroHardware extends Application implements EventHandler<Event>{
	
	ControllerHardware crtlHw = new ControllerHardware();
	
	private TextField txtId = new TextField();
	private TextField txtTipo = new TextField();
	private TextField txtFabricante = new TextField();
	private TextField txtPreco = new TextField();
	private TextField txtDtCompra = new TextField();
	private TextField txtDescricao = new TextField();
	
	private Button btnAdd = new Button("Adicionar");
	private Button btnPesq = new Button("Pesquisar");

	public static void main(String[] args) {
		CadastroHardware.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		BorderPane panePrincipal = new BorderPane();
		GridPane pane = new GridPane();
		FlowPane paneButtons = new FlowPane();
		Scene scene = new Scene(panePrincipal);
		
		panePrincipal.setStyle("-fx-padding : 10px");
		pane.setStyle("-fx-padding : 10px");
		
		panePrincipal.setCenter(pane);
		panePrincipal.setBottom(paneButtons);
		
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		col1.setPercentWidth(20);
		col2.setPercentWidth(80);
		col2.setMinWidth(300);
		
		pane.getColumnConstraints().addAll(col1,col2);
		pane.setHgap(5);
		pane.setVgap(10);
		
		pane.add(new Label("Id :") , 0,0);
		pane.add(new Label("Tipo :") , 0,1);
		pane.add(new Label("Fabricante :") , 0,2);
		pane.add(new Label("Preço :") , 0,3);
		pane.add(new Label("DT Compra :") , 0,4);
		pane.add(new Label("Descrição :") , 0,5);
		
		pane.add(txtId, 1, 0);
		pane.add(txtTipo, 1, 1);
		pane.add(txtFabricante, 1, 2);
		pane.add(txtPreco, 1, 3);
		pane.add(txtDtCompra, 1, 4);
		pane.add(txtDescricao, 1, 5,1,2);
		
		paneButtons.setHgap(20);
		paneButtons.getChildren().add(btnAdd);
		paneButtons.getChildren().add(btnPesq);
		
		btnAdd.addEventHandler(ActionEvent.ANY, this);
		btnPesq.addEventHandler(ActionEvent.ANY, this);
		
		stage.setTitle("");
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void handle(Event event) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (event.getTarget() == btnAdd) {
			try {
				Hardware hw = new Hardware();
				hw.setId(Long.parseLong(txtId.getText()));
				hw.setTipo(txtTipo.getText());
				hw.setFabricante(txtFabricante.getText());
				hw.setPreco(Double.parseDouble(txtPreco.getText()));
				Date d = sdf.parse(txtDtCompra.getText());
				hw.setDataCompra(d);
				hw.setDescricao(txtDescricao.getText());

				crtlHw.addHardware(hw);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (event.getTarget() == btnPesq) {
			Hardware hardware = crtlHw.buscarHardware(txtTipo.getText());

			txtTipo.setText(hardware.getTipo());
			txtId.setText(hardware.getId() + "");
			txtFabricante.setText(hardware.getFabricante());
			txtPreco.setText(hardware.getPreco() + "");
			txtDtCompra.setText(sdf.format(hardware.getDataCompra()));
			txtDescricao.setText(hardware.getDescricao());
		}
	}

}
