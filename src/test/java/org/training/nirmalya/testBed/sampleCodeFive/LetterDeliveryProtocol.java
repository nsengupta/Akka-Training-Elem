package org.training.nirmalya.testBed.sampleCodeFive;

import java.io.Serializable;

public class LetterDeliveryProtocol {
	
	public static class EnvelopeToDeliver implements Serializable { 
		
		private static final long serialVersionUID = 1L;
		public final String letter;
		
		public EnvelopeToDeliver(String letter) {
			super();
			this.letter = letter;
		}

		public String toString() {
			return (this.letter);
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((letter == null) ? 0 : letter.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EnvelopeToDeliver other = (EnvelopeToDeliver) obj;
			if (letter == null) {
				if (other.letter != null)
					return false;
			} else if (!letter.equals(other.letter))
				return false;
			return true;
		}

		
	}
	
	public static class EnvelopeDeliveredConfirmation implements Serializable { 
		private static final long serialVersionUID = 1L;
		public final String letter;
		public final String deliveredBy;
		
		public EnvelopeDeliveredConfirmation(
				String letter, String deliveredBy) {
			super();
			this.letter = letter;
			this.deliveredBy = deliveredBy;
		}

		public String toString() {
			return (this.letter + ", by:" + deliveredBy);
		}
	}

}
