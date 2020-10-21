package com.thoughtworks.basic.quiz.briefing.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharSizeValidator implements ConstraintValidator<CharSize, String> {

   private int maxCharSize;

   private int minCharSize;

   public void initialize(CharSize charSize) {
      this.maxCharSize = charSize.max();
      this.minCharSize = charSize.min();
   }

   @Override
   public boolean isValid(String StringValue, ConstraintValidatorContext context) {
      if(StringValue == null){
         return true;
      }

      int f = StringValue.getBytes().length;

      return f >= minCharSize && f <= maxCharSize;

   }
}
