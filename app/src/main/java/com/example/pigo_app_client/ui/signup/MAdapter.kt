package com.example.pigo_app_client.ui.signup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pigo_app_client.R
import com.example.pigo_app_client.databinding.ActivitySignupBinding

class MAdapter(private val binding: ActivitySignupBinding, private val viewModel: SignUpViewModel): RecyclerView.Adapter<MAdapter.ViewHolder>() {

    val layoutList = arrayListOf<Int>(R.layout.layout_sign_up1, R.layout.layout_sign_up2, R.layout.layout_sign_up3)

    override fun getItemCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = when(viewType){
            0 -> inflate(parent, R.layout.layout_sign_up1)
            1 -> inflate(parent, R.layout.layout_sign_up2)
            2 -> inflate(parent, R.layout.layout_sign_up3)
            else -> inflate(parent, R.layout.layout_sign_up1)
        }
        return ViewHolder(v)
    }

    private fun inflate(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemPosition: Int) {
        holder.bindItemStatusListItems(binding, viewModel)

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItemStatusListItems(binding: ActivitySignupBinding, viewModel: SignUpViewModel) {
            when(itemViewType){
                0 -> signUp_1(binding, viewModel)

                1 -> signUp_2(binding, viewModel)

                2 -> signUp_3(binding)
            }
        }

        private fun signUp_1(binding: ActivitySignupBinding, viewModel: SignUpViewModel){
            //itemView.findViewById<EditText>(R.id.editText_VerificationNum_SignUpFragment3)
            val nextButton = itemView.findViewById<Button>(R.id.button_Next_SignUpFragment1)
            val userName = itemView.findViewById<EditText>(R.id.editText_UserName_SignUpFragment1)
            val userId = itemView.findViewById<EditText>(R.id.editText_UserId_SignUpFragment1)

            nextButton.setOnClickListener {
                if(userName.text.toString() == "" || userId.text.toString() == ""){
                    Toast.makeText(itemView.context, "빈칸을 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
                } else{
                    viewModel.userName = userName.text.toString()
                    viewModel.userId = userId.text.toString()

                    //회원가입 다음 페이지로 이동
                    binding.viewPagerSignUpActivity.currentItem++
                }
            }
        }


        private fun signUp_2(binding: ActivitySignupBinding, viewModel: SignUpViewModel) {
            val nextButton = itemView.findViewById<Button>(R.id.button_Next_SignUpFragment2)
            val userPw = itemView.findViewById<EditText>(R.id.editText_Password_SignUpFragment2)
            val userPwCk = itemView.findViewById<EditText>(R.id.editText_PasswordCk_SignUpFragment2)

            nextButton.setOnClickListener {
                if(userPw.text.toString() == "" || userPwCk.text.toString() == ""){
                    Toast.makeText(itemView.context, "빈칸을 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
                } else if(userPw.text.toString() !=  userPwCk.text.toString()){
                    Toast.makeText(itemView.context, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show()
                }else {
                    viewModel.userPw = userPw.text.toString()

                    //회원가입 다음 페이지로 이동
                    binding.viewPagerSignUpActivity.currentItem++
                }
            }

        }


        private fun signUp_3(binding: ActivitySignupBinding) {
            val nextButton = itemView.findViewById<Button>(R.id.button_Next_SignUpFragment3)
            val verification = itemView.findViewById<EditText>(R.id.editText_VerificationNum_SignUpFragment3)

            nextButton.setOnClickListener {
                if(verification.text.toString() == ""){
                    Toast.makeText(itemView.context, "인증번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(itemView.context, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                    //회원가입 완료
                    binding.activity?.finish()
                }
            }
        }

    }
}
