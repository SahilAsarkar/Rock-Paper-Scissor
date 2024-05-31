package com.example.rockpaperscissor;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rockpaperscissor.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String n1, n2;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Assuming EdgeToEdge.enable(this) is a valid utility method
        setContentView(binding.getRoot());

        dialog = new Dialog(this);
        binding.rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human.setImageResource(R.drawable.rock_hand);
                n1 = "rock";
                display();
            }
        });
        binding.paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human.setImageResource(R.drawable.paper_hand);
                n1 = "paper";
                display();
            }
        });

        binding.scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.human.setImageResource(R.drawable.scissor_hand);
                n1 = "scissor";
                display();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void display() {
        Random rand = new Random();
        int n = rand.nextInt(3);
        if (n == 0) {
            n2 = "rock";
            binding.robot.setImageResource(R.drawable.rock_hand);
            binding.robot.setRotation(180);
        } else if (n == 1) {
            n2 = "paper";
            binding.robot.setImageResource(R.drawable.paper_hand);
            binding.robot.setRotation(180);
        } else if (n == 2) {
            n2 = "scissor";
            binding.robot.setImageResource(R.drawable.scissor_hand);
            binding.robot.setRotation(180);
        }
        result();
    }

    public void result() {
        if (n1.equals(n2)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showTiedialog();
                    resetImages();
                }
            }, 1000);
        } else if (n1.equals("rock") && n2.equals("scissor") || n1.equals("paper") && n2.equals("rock") || n1.equals("scissor") && n2.equals("paper")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showWinDialog();
                    resetImages();
                }
            }, 1000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showLoseDialog();
                    resetImages();
                }
            }, 1000);
        }
    }

    private void resetImages() {
        binding.human.setImageResource(R.drawable.human);
        binding.robot.setImageResource(R.drawable.robot);
    }

    private void showLoseDialog() {
        dialog.setContentView(R.layout.loss_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showWinDialog() {
        dialog.setContentView(R.layout.win_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showTiedialog() {
        dialog.setContentView(R.layout.tie_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
