package com.thoughtworks.basic.quiz.briefing.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharSizeValidator implements ConstraintValidator<CharSize, String> {

   private int maxCharSize;

   public void initialize(CharSize charSize) {
      this.maxCharSize = charSize.max();
   }

   public boolean isValid(String StringValue, ConstraintValidatorContext context) {
      if(StringValue == null){
         return true;
      }

      int f = StringValue.getBytes().length;

      return f <= maxCharSize;

   }
}
