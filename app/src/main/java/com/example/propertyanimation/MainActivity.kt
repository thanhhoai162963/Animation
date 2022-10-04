package com.example.propertyanimation

import android.animation.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.propertyanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setListener()
    }

    private fun setListener() {
        binding.btnStartAnim.setOnClickListener {
            //setAnimation()
            // loadAnimationFromXML()
            val anim1 = ValueAnimator.ofFloat(500f).apply {
                duration = 2000
                addUpdateListener {
                    binding.imgView1.translationX = it.animatedValue as Float
                    /*  binding.imgView1.translationY = it.animatedValue as Float
                      binding.imgView1.scaleX = 1.5f
                      binding.imgView1.scaleY = 1.5f
                      binding.imgView1.rotation = 90f
                      binding.imgView1.rotationX = 90f
                      binding.imgView1.rotationY = 90f
                      binding.imgView1.x = 90f
                      binding.imgView1.y = 90f*/
                }
                setEvaluator(IntEvaluator())
                start()

            }
        }

        binding.btnClearAnim.setOnClickListener {
            clearAnimation()
        }
    }

    private fun setAnimation() {
        val anim1 = ValueAnimator.ofFloat(500f).apply {
            duration = 2000
            start()
            addUpdateListener {
                binding.imgView1.translationX = it.animatedValue as Float
              /*  binding.imgView1.translationY = it.animatedValue as Float
                binding.imgView1.scaleX = 1.5f
                binding.imgView1.scaleY = 1.5f
                binding.imgView1.rotation = 90f
                binding.imgView1.rotationX = 90f
                binding.imgView1.rotationY = 90f
                binding.imgView1.x = 90f
                binding.imgView1.y = 90f*/

            }
        }

        val anim2 = ValueAnimator.ofFloat(600f).apply {
            duration = 400
            start()
            addUpdateListener {
                binding.imgView2.translationX = it.animatedValue as Float
            }
        }

        val anim3 = ObjectAnimator.ofFloat(binding.imgView3, "translationX", 700f).apply {
            duration = 400
            start()
        }
        val animSet = AnimatorSet().apply {
            //play(anim2).with(anim3)
            // play().after()
            // play().before()
            playSequentially(anim2, anim3)
        }
        animSet.apply {
            start()
        }

    }

    private fun clearAnimation() {
        ValueAnimator.ofFloat(0f).apply {
            duration = 400
            start()
            addUpdateListener {
                binding.imgView1.translationX = it.animatedValue as Float
            }
        }

        ValueAnimator.ofFloat(0f).apply {
            duration = 400
            start()
            addUpdateListener {
                binding.imgView2.translationX = it.animatedValue as Float
            }
        }

        ObjectAnimator.ofFloat(binding.imgView3, "translationX", 0f).apply {
            duration = 400
            start()
        }
    }

    private fun loadAnimationFromXML() {
        (AnimatorInflater.loadAnimator(this, R.animator.animator) as ValueAnimator).apply {
            addUpdateListener {
                binding.imgView1.translationZ = it.animatedValue as Float
                binding.imgView1.scaleX = 1.5f
            }
            start()
        }

        (AnimatorInflater.loadAnimator(this, R.animator.object_animator) as ObjectAnimator).apply {
            target = binding.imgView2
            start()

        }
    }

}