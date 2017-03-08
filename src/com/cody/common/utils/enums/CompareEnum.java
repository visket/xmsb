package com.cody.common.utils.enums;



public enum CompareEnum {

	// =
	EQ {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "=";
		}
	},
	
	// <>
	NEQ {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "<>";
		}
	},	
	
	// >
	GT {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ">";
		}
	},		
	
	// >=
	EGT {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ">=";
		}
	},	
	
	// <
	LT {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "<";
		}
	},		
	
	// <=
	ELT {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "<=";
		}
	},	
	
	// like
	LIKE {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "like";
		}
	},	
	
	// is null
	NULL {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "is null";
		}
	},	
	
	//is not null
	NOTNULL {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "is not null";
		}
	}, 
	
	//	&
	AMP {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "&";
		}
	},	
	
	//	'
	APOS {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "'";
		}
	},	
	
	//	"
	QUOT {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "\"";
		}
	}
	
	
	
	
}
