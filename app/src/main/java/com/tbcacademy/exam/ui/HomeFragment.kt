package com.tbcacademy.exam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.tbcacademy.exam.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private var mainView: ViewGroup? = null
    private var board: Board? = null
    private var boardView: BoardView? = null
    private var boardSize = 3


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainView = binding.mainContainer
        newGame()
    }

    private fun newGame() {
        board = Board(boardSize)
        board!!.addBoardChangeListener(boardChangeListener)
        board!!.rearrange()
        mainView!!.removeView(boardView)
        boardView = BoardView(requireContext(), board!!)
        mainView!!.addView(boardView)
    }

    private val boardChangeListener: BoardChangeListener = object : BoardChangeListener {
        override fun tileSlid(from: Place?, to: Place?, numOfMoves: Int) {
        }

        override fun solved(numOfMoves: Int) {
            AlertDialog.Builder(requireActivity())
                .setTitle("You won .. !!")
                .setMessage("you are win in $numOfMoves moves... !! \nIf you want a New Game..!!")
                .setPositiveButton("Yes") { dialog, _ ->
                    board!!.rearrange()
                    boardView!!.invalidate()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }
}