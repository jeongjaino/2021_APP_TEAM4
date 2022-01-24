package com.example.wap.ui.mini_game

import android.animation.ObjectAnimator
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.wap.databinding.FragmentShakeGameBinding
import com.example.wap.dialog.character.SaveGameDialog
import com.example.wap.ui.character.CharacterViewModel
import com.example.wap.util.ShakeDetector

class ShakeGameFragment : Fragment() {

    private val binding by lazy{ FragmentShakeGameBinding.inflate(layoutInflater)}

    private lateinit var shakeDetector: ShakeDetector
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor

    private lateinit var characterViewModel: CharacterViewModel

    private lateinit var callback: OnBackPressedCallback

    private var gold = 0

    //뒤로 가기 눌렀을때 callback 받아서 처리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val dialog = SaveGameDialog()
                dialog.setListener(object: SaveGameDialog.SaveDialogListener{
                    override fun onPositiveClick() {
                        characterViewModel.updateGold(gold)
                        val direction: NavDirections = ShakeGameFragmentDirections.actionShakeGameFragmentToGameFragment()
                        view!!.findNavController().navigate(direction)
                    }
                })
                dialog.show(activity!!.supportFragmentManager, "saveDialog")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        characterViewModel = ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
        initSensor()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(shakeDetector,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun initSensor(){
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeDetector = ShakeDetector()
        shakeDetector.setOnShakeListener(object: ShakeDetector.OnShakeListener{
            override fun onShake(count: Int) {
                shakeAnimation(binding.shakeImage)
                binding.shakeGold.text = "총 획득한 골드는 ${count}G 입니다."
                gold  = count
            }
        })
    }
    private fun shakeAnimation(character: ImageView){
        val width = (binding.shakeLayout.width - binding.shakeImage.width)/2
        val height = (binding.shakeLayout.height - binding.shakeImage.height)/2

        val nextPosX = (-width..width).random().toFloat()
        val nextPosY = (-height..height).random().toFloat()
        val nextAngle = (180..360).random().toFloat()

        ObjectAnimator.ofFloat(character, "translationX", nextPosX).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(character, "translationY", nextPosY).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(character, "rotation", 0f, nextAngle).apply {
            duration = 1000
            start()
        }
    }
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(shakeDetector)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}