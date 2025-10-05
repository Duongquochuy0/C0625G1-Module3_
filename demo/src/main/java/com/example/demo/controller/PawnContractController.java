package com.example.demo.controller;

import com.example.demo.dto.PawnContractDto;
import com.example.demo.service.IPawnContractService;
import com.example.demo.service.PawnContractService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pawn-contracts")
public class PawnContractController extends HttpServlet {

    private IPawnContractService service = new PawnContractService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PawnContractDto> contracts = service.getAllContracts();
        request.setAttribute("contracts", contracts);
        request.getRequestDispatcher("/views/pawn_contract/list.jsp").forward(request, response);
    }
}
